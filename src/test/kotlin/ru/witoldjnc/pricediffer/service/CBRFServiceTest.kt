package ru.witoldjnc.pricediffer.service

import org.jsoup.Jsoup
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import ru.witoldjnc.pricediffer.repository.Connector

internal class CBRFServiceTest : TestUtils() {

    private val connector = Mockito.mock(Connector::class.java)

    private val cbrfCourseFetcher = CBRFService(connector)

    @BeforeEach
    fun init() {
        val course = readFileDirectlyAsText("src/test/resources/valuteCourse.xml")
        val htmlCourse = Jsoup.parse(course)
        Mockito.`when`(connector.connect("http://www.cbr.ru/scripts/XML_daily.asp")).thenReturn(htmlCourse);
    }

    @Test
    fun getUsdCourse() {
        val usdCourse = cbrfCourseFetcher.getUsdCourse()
        assertEquals(104.0741, usdCourse)
    }
}