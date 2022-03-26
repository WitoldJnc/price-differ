package ru.witoldjnc.pricediffer.service

import org.apache.commons.lang3.StringUtils
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import ru.witoldjnc.pricediffer.dto.RoadmapCategory
import ru.witoldjnc.pricediffer.model.ErrorProductItem
import ru.witoldjnc.pricediffer.model.ProductItem
import ru.witoldjnc.pricediffer.repository.Connector
import ru.witoldjnc.pricediffer.repository.ShopFetcher

@Service
class ShopFetcherService(
        @Qualifier("jsoupWrapper") private val connector: Connector
) : ShopFetcher {

    override fun getPriceByCategory(roadmapCategory: RoadmapCategory): MutableList<ProductItem> {
        val productList: MutableList<ProductItem> = mutableListOf()
        val page = connector.connect(roadmapCategory.baseUrl);
        for (item in roadmapCategory.items) {
            val productCard = page!!.select("span:contains(${item.name})").first().parent().parent()
            val rawPrice = parsePrice(productCard)

            item.price = trimPrice(rawPrice)
            productList.add(ProductItem(item))
        }

        return productList;
    }

    override fun parsePrice(itemElement: Element): String {
        val priceNew = itemElement.select(".price-new").text()
        val priceOld = itemElement.select(".price-old").text()
        return StringUtils.firstNonBlank(priceOld, priceNew)
    }

    override fun trimPrice(price: String): Double {
        var splited = price.split(" ")[0]
        splited = splited.replace(" ", "")
        return splited.replace(",", ".").toDouble()
    }

    override fun checkFaultUrls(categories: List<RoadmapCategory>): MutableList<ErrorProductItem> {
        val errorsList: MutableList<ErrorProductItem> = mutableListOf()

        for (category in categories) {
            val page = connector.connect(category.baseUrl)
            for (item in category.items) {
                val element = page!!.select("span:contains(${item.name})").text()
                if (element.isBlank()) {
                    val errorProductItem = ErrorProductItem(item);
                    errorProductItem.category = category.name
                    errorsList.add(errorProductItem)
                }
            }
        }

        return errorsList
    }

}