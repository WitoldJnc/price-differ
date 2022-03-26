package ru.witoldjnc.pricediffer.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.repository.Connector

@Service
class JsoupWrapper : Connector {

    private val logger: Logger = LoggerFactory.getLogger(JsoupWrapper::class.java)

    override fun connect(url: String) = runBlocking {
        logger.debug("jsoup start connect to { }", url)
        val page = Jsoup.connect(url)
                .get();
        launch {
            delay((Math.random() * 1000).toLong())
        }
        return@runBlocking page
    }

    override fun close() {

    }

}