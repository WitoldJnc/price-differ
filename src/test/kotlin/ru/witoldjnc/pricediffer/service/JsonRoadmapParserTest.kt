package ru.witoldjnc.pricediffer.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class JsonRoadmapParserTest {

    private val parser = JsonRoadmapParser();

    @Test
    fun parseRoadMap() {
       parser.parseRoadMap();
    }
}