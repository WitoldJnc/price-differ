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
            val itemUrl = item.url
            println(itemUrl)
            val itemElement = page!!.select("a[href$=$itemUrl]").first().parent()

            val rawPrice = parsePrice(itemElement)

            item.price = trimPrice(rawPrice)
            item.url = "https://www.perekrestok.ru${item.url}"


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
        val splited = price.split(" ")[0]
        return splited.replace(",", ".").toDouble()
    }

    override fun checkFaultUrls(categories: List<RoadmapCategory>): MutableList<ErrorProductItem> {
        val errorsList: MutableList<ErrorProductItem> = mutableListOf()

        for (category in categories) {
            val page = connector.connect(category.baseUrl)

            for (item in category.items) {
                val element = page!!.select("a[href$=${item.url}").text()
                if (element.isBlank()) {
                    item.url = "https://www.perekrestok.ru${item.url}"
                    val errorProductItem = ErrorProductItem(item);
                    errorProductItem.category = category.name
                    errorsList.add(errorProductItem)
                }
            }
        }

        return errorsList
    }


}