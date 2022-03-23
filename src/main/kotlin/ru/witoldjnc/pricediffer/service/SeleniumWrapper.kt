package ru.witoldjnc.pricediffer.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.openqa.selenium.WebDriver
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.repository.Connector

@Service
class SeleniumWrapper(
        private val webDriver: WebDriver
) : Connector {

    override fun connect(url: String) = runBlocking {
        webDriver.navigate().to(url)
        launch {
            delay((Math.random() * 1000).toLong())
        }
        val pageSource = webDriver.pageSource
        return@runBlocking Jsoup.parse(pageSource)
    }

    override fun close() {
        webDriver.close();
    }

}