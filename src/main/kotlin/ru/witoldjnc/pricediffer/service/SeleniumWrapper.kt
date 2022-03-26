package ru.witoldjnc.pricediffer.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.repository.Connector

@Service
class SeleniumWrapper(
        private val webDriver: WebDriver
) : Connector {

    private val logger: Logger = LoggerFactory.getLogger(SeleniumWrapper::class.java)

    override fun connect(url: String) = runBlocking {
        logger.debug("selenium: start connect to { }", url)

        webDriver.navigate().to(url)
        launch {
            delay((Math.random() * 1000).toLong())
        }
        val pageSource = webDriver.pageSource
        return@runBlocking Jsoup.parse(pageSource)
    }

    override fun close() {
        logger.debug("selenium: close session")
        webDriver.close();
    }

}