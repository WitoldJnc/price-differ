package ru.witoldjnc.pricediffer.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.repository.Connector

@Service
class SeleniumWrapper(
        private val webDriver: WebDriver
) : Connector {

    override fun connect(url: String): Document? {
        webDriver.navigate().to(url)
        val pageSource = webDriver.pageSource
        webDriver.close()
        return Jsoup.parse(pageSource)
    }

}