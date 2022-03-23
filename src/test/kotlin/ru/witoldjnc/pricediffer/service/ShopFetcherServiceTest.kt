package ru.witoldjnc.pricediffer.service

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import ru.witoldjnc.pricediffer.dto.RoadmapProduct
import ru.witoldjnc.pricediffer.repository.Connector
import java.io.File

internal class ShopFetcherServiceTest() : TestUtils() {
    private val connector = Mockito.mock(Connector::class.java)
    private val parser = Mockito.mock(JsonRoadmapParser::class.java)

    private val fetcher = ShopFetcherService(connector, parser);

    @BeforeEach
    fun init() {
        val htmlWithoutSale = readFileDirectlyAsText("src/test/resources/withoutSaleDoc.html")
        val htmlWithSale = readFileDirectlyAsText("src/test/resources/withSaleDoc.html")
        val roadmapProducts = listOf<RoadmapProduct>(
                RoadmapProduct("without sale name", "without sale description", "withoutSale-url"),
                RoadmapProduct("with sale name", "with sale discription", "withSale-url"));
        val documentWithoutSale = Jsoup.parse(htmlWithoutSale)
        val documentWithSale = Jsoup.parse(htmlWithSale)

        Mockito.`when`(connector.connect("withoutSale-url")).thenReturn(documentWithoutSale)
        Mockito.`when`(connector.connect("withSale-url")).thenReturn(documentWithSale)
        Mockito.`when`(parser.parseRoadMap()).thenReturn(roadmapProducts)
    }

    @Test
    fun fetchPriceShouldReturnCurrentPriceTest() {
        val roadmapProduct = RoadmapProduct("name", "description", "withoutSale-url")
        val fetchPriceFromItem = fetcher.fetchPriceFromItem(roadmapProduct)
        assertEquals(41.90, fetchPriceFromItem)

    }

    @Test
    fun fetchPriceShouldReturnPriceWithoutSaleTest() {
        val roadmapProduct = RoadmapProduct("name", "description", "withSale-url")
        val fetchPriceFromItem = fetcher.fetchPriceFromItem(roadmapProduct)
        assertEquals(169.90, fetchPriceFromItem)
    }

    @Test
    fun enrichPriceFromRoadmapTest() {
        val enrichPriceFromRoadmapItems = fetcher.enrichPriceFromRoadmapItems()
        assertTrue(enrichPriceFromRoadmapItems.size == 2)
        assertEquals(41.9, enrichPriceFromRoadmapItems.find { it.url.equals("withoutSale-url") }?.price)
        assertEquals(169.9, enrichPriceFromRoadmapItems.find { it.url.equals("withSale-url") }?.price)
    }

}