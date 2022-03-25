package ru.witoldjnc.pricediffer.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import ru.witoldjnc.pricediffer.dto.RoadmapCategory
import ru.witoldjnc.pricediffer.dto.RoadmapProductItem
import ru.witoldjnc.pricediffer.repository.Connector

internal class ShopFetcherServiceTest() : TestUtils() {
    private val connector = Mockito.mock(Connector::class.java)

    private val fetcher = ShopFetcherService(connector);

    private lateinit var fruitCategory: RoadmapCategory;
    private lateinit var fruitDoc: Document
    private lateinit var vegetableDoc: Document


    @BeforeEach
    fun init() {
        fruitCategory = RoadmapCategory(1, "фрукты", "https://www.perekrestok.ru/cat/c/153/frukty", mutableListOf(
                RoadmapProductItem(1,  "Яблоки Гала", "/cat/350/p/abloki-gala-kg-3366336"),
                RoadmapProductItem(2, "Яблоки голден", "/cat/350/p/abloki-golden-kg-3366324")
        ))

        val htmlFruitCategory = readFileDirectlyAsText("src/test/resources/fruits.html")
        fruitDoc = Jsoup.parse(htmlFruitCategory)

        val htmlVegetableCategory = readFileDirectlyAsText("src/test/resources/vegetables.html")
        vegetableDoc = Jsoup.parse(htmlVegetableCategory)

        Mockito.`when`(connector.connect("https://www.perekrestok.ru/cat/c/153/frukty")).thenReturn(fruitDoc)
        Mockito.`when`(connector.connect("https://www.perekrestok.ru/cat/c/150/ovosi")).thenReturn(vegetableDoc)

    }

    @Test
    fun parsePriceShouldReturnOldPriceWhenSaleTest(){
        val saleDoc = fruitCategory.items.find { it.url.equals("/cat/350/p/abloki-golden-kg-3366324") }
        val saleElement = fruitDoc.select("a[href$=${saleDoc!!.url}]").first().parent()

        assertTrue(!saleElement.select(".price-new").text().isBlank())
        assertTrue(!saleElement.select(".price-old").text().isBlank())

        val salePrice = fetcher.parsePrice(saleElement)

        assertEquals(salePrice, saleElement.select(".price-old").text())
    }

    @Test
    fun parsePriceShouldReturnCurrentPriceWhenHaveNoSaleTest(){
        val wihtoutSaleDoc = fruitCategory.items.find { it.url.equals("/cat/350/p/abloki-gala-kg-3366336") }
        val withoutSaleElement = fruitDoc.select("a[href$=${wihtoutSaleDoc!!.url}]").first().parent()

        assertTrue(!withoutSaleElement.select(".price-new").text().isBlank())
        assertTrue(withoutSaleElement.select(".price-old").text().isBlank())

        val withoutSalePrice = fetcher.parsePrice(withoutSaleElement)

        assertEquals(withoutSaleElement.select(".price-new").text(), withoutSalePrice)
    }

    @Test
    fun trimPriceShouldReturnDoublePrice(){
        val rawPrice = fruitDoc.select(".price-new").first().text()
        assertTrue(rawPrice.contains(" ₽"))
        assertTrue(rawPrice.contains(","))

        val trimPrice = fetcher.trimPrice(rawPrice)
        assertTrue(!trimPrice.toString().contains(" ₽"))
        assertTrue(!trimPrice.toString().contains(","))
    }

    @Test
    fun getPriceByCategoryShouldReturnEnrichedList(){
        val galaAppleBeforeMutate = fruitCategory.items.find { it.name.equals("Яблоки Гала") }
        assertTrue(!galaAppleBeforeMutate!!.url.contains("https://perek"))
        assertEquals(galaAppleBeforeMutate.price, 00.00)

        val priceByCategory = fetcher.getPriceByCategory(fruitCategory)
        val galaAppleAfterMutate = fruitCategory.items.find { it.name.equals("Яблоки Гала") }

        assertTrue(galaAppleAfterMutate!!.url.contains("https://"))
        assertTrue(galaAppleAfterMutate.price != 00.00)
        assertTrue(priceByCategory.size == 2)

    }

    @Test
    fun checkUrlFaults(){
        val wrongHref = "/cat/366/p/morkov-mytaa-kg-52112"
        val categoryName = "овощи"
        val wrongItemName = "Морковь"
        val categories = mutableListOf<RoadmapCategory>(
                fruitCategory,
                RoadmapCategory(2, categoryName, "https://www.perekrestok.ru/cat/c/150/ovosi", mutableListOf(
                        RoadmapProductItem(3,  "Картофель", "/cat/366/p/kartofel-v-setke-kg-3987"),
                        RoadmapProductItem(4,  wrongItemName, wrongHref)
                ))
        )
        val selectWrongEl = vegetableDoc.select("a[href$=${wrongHref}]").text()

        assertTrue(selectWrongEl.isBlank())

        val errors = fetcher.checkFaultUrls(categories)

        assertEquals(1, errors.size)
        assertEquals(categoryName, errors.first().category)
        assertEquals(wrongItemName, errors.first().name)
        assertTrue(errors.first().url.startsWith("https://www.perek"))


    }


}