package ru.witoldjnc.pricediffer.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.repository.Connector

@Service
class JsoupWrapper : Connector {

    @Value("\${prk.cookie}")
    private lateinit var cookieValue: String

    override fun connect(url: String): Document? {
        return Jsoup.connect(url)
                .get()
    }

}