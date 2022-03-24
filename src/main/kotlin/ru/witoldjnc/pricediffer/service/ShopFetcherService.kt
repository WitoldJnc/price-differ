package ru.witoldjnc.pricediffer.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.model.ProductItem
import ru.witoldjnc.pricediffer.dto.RoadmapProduct
import ru.witoldjnc.pricediffer.model.ErrorAddressProductList
import ru.witoldjnc.pricediffer.model.ErrorProductItem
import ru.witoldjnc.pricediffer.repository.Connector
import ru.witoldjnc.pricediffer.repository.ShopFetcher
import java.util.stream.Collectors

@Service
class ShopFetcherService(
        @Qualifier("jsoupWrapper") private val connector: Connector
) : ShopFetcher {

    override fun enrichPriceFromRoadmapItems(roadmapProducts: List<RoadmapProduct>): List<ProductItem> {
        val res = roadmapProducts.stream()
                .peek { it.price = fetchPriceFromItem(it) }
                .map { ProductItem(it) }
                .collect(Collectors.toList())
        connector.close();
        return res;
    }

    override fun fetchPriceFromItem(item: RoadmapProduct): Double {
        val page = connector.connect(item.url)
        val count = page?.select("meta[itemprop$=price]")?.first()?.attr("content")
        return count?.toDouble() ?: 0.0;
    }


     fun checkFaultUrls(roadmapItems: List<RoadmapProduct>): ErrorAddressProductList {
        val errorsList : MutableList<ErrorProductItem> = mutableListOf()
        for (item in roadmapItems) {
            try {
                var connect = connector.connect(item.url)
            } catch (e: Exception) {
                errorsList.add(ErrorProductItem(item))
            }
        }
        return ErrorAddressProductList(errorsList)
    }
}