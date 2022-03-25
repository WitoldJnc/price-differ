package ru.witoldjnc.pricediffer.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.witoldjnc.pricediffer.repository.Connector

@Component
class CBRFService(
        @Qualifier("jsoupWrapper") private val connector: Connector
) {

    @Value("\${cbrf.url}")
    private lateinit var url: String

    fun getUsdCourse(): Double {
        val page = connector.connect(url);

        var courseAsText = page?.select("Valute > :containsOwn(USD)")
                ?.first()?.parent()?.select("Value")?.text()

        courseAsText = courseAsText?.replace(",", ".")

        return courseAsText?.toDouble() ?: 00.0;
    }
}