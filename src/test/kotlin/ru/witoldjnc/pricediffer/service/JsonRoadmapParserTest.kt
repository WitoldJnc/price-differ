package ru.witoldjnc.pricediffer.service

import org.junit.jupiter.api.Test

internal class JsonRoadmapParserTest {

    private val parser = JsonRoadmapParser();

    @Test
    fun parseRoadMap() {
       parser.getItemsFromRoadmap();
    }
}