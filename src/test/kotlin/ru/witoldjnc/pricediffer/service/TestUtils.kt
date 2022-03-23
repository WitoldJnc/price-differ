package ru.witoldjnc.pricediffer.service

import java.io.File

open class TestUtils {
    fun readFileDirectlyAsText(fileName: String): String = File(fileName).readText(Charsets.UTF_8)

}