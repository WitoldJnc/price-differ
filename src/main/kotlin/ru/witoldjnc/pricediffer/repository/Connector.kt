package ru.witoldjnc.pricediffer.repository

import org.jsoup.nodes.Document

interface Connector {
    fun connect(url: String): Document?
}