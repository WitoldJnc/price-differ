package ru.witoldjnc.pricediffer.service

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import ru.witoldjnc.pricediffer.dto.RoadmapProduct
import ru.witoldjnc.pricediffer.repository.Connector

internal class ShopFetcherServiceTest() : TestUtils() {
    private val connector = Mockito.mock(Connector::class.java)

    private val fetcher = ShopFetcherService(connector);

    private lateinit var roadmapProducts: List<RoadmapProduct>

    @BeforeEach
    fun init() {
        val htmlWithoutSale = readFileDirectlyAsText("src/test/resources/withoutSaleDoc.html")
        val htmlWithSale = readFileDirectlyAsText("src/test/resources/withSaleDoc.html")
        roadmapProducts = listOf<RoadmapProduct>(
                RoadmapProduct("without sale name", "without sale description", "withoutSale-url", 1),
                RoadmapProduct("with sale name", "with sale discription", "withSale-url", 2));

        val documentWithoutSale = Jsoup.parse(htmlWithoutSale)
        val documentWithSale = Jsoup.parse(htmlWithSale)

        Mockito.`when`(connector.connect("withoutSale-url")).thenReturn(documentWithoutSale)
        Mockito.`when`(connector.connect("withSale-url")).thenReturn(documentWithSale)
    }

    @Test
    fun fetchPriceShouldReturnCurrentPriceTest() {
        val roadmapProduct = RoadmapProduct("name", "description", "withoutSale-url", 1)
        val fetchPriceFromItem = fetcher.fetchPriceFromItem(roadmapProduct)
        assertEquals(41.90, fetchPriceFromItem)

    }

    @Test
    fun fetchPriceShouldReturnPriceWithoutSaleTest() {
        val roadmapProduct = RoadmapProduct("name", "description", "withSale-url", 2)
        val fetchPriceFromItem = fetcher.fetchPriceFromItem(roadmapProduct)
        assertEquals(169.90, fetchPriceFromItem)
    }

    @Test
    fun enrichPriceFromRoadmapTest() {
        val enrichPriceFromRoadmapItems = fetcher.enrichPriceFromRoadmapItems(roadmapProducts)
        assertTrue(enrichPriceFromRoadmapItems.size == 2)
        assertEquals(41.9, enrichPriceFromRoadmapItems.find { it.url.equals("withoutSale-url") }?.price)
        assertEquals(169.9, enrichPriceFromRoadmapItems.find { it.url.equals("withSale-url") }?.price)
    }

    @Test
    fun checkFaultUrlsShouldGetItemOnException() {
        Mockito.`when`(connector.connect(roadmapProducts.get(0).url)).thenThrow(MockitoException::class.java)
        val checkFaultUrls = fetcher.checkFaultUrls(roadmapProducts)
        assertTrue(checkFaultUrls.items.size == 1)
        assertEquals(checkFaultUrls.items.get(0).url, roadmapProducts.get(0).url)
    }

}