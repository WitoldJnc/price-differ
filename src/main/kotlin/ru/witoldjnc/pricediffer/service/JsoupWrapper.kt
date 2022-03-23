package ru.witoldjnc.pricediffer.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.repository.Connector

@Service
class JsoupWrapper : Connector {

    @Value("\${prk.cookie}")
    private lateinit var cookieValue: String

    override fun connect(url: String) = runBlocking {
        var page = Jsoup.connect(url)
                .get();
        launch {
            delay((Math.random() * 1000).toLong())
        }
        return@runBlocking page
    }

    override fun close() {

    }

}