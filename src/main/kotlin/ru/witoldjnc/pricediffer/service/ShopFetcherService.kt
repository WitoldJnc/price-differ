package ru.witoldjnc.pricediffer.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.dto.RoadmapProduct
import ru.witoldjnc.pricediffer.repository.Connector
import ru.witoldjnc.pricediffer.repository.ShopFetcher

@Service
class ShopFetcherService(
        @Qualifier("jsoupWrapper") private val  connector: Connector,
        private val roadMapParser: JsonRoadmapParser
) : ShopFetcher {

    override fun enrichPriceFromRoadmapItems(): List<RoadmapProduct> {
        val res = roadMapParser.parseRoadMap()
        res.stream()
                .forEach { it.price = fetchPriceFromItem(it) };
        return res;
    }

    override fun fetchPriceFromItem(item: RoadmapProduct): Double {
        val page = connector.connect(item.url)
        val count = page?.select("meta[itemprop$=price]")?.first()?.attr("content")
        return count?.toDouble() ?: 0.0;
    }
}