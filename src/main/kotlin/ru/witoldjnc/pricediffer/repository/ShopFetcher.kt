package ru.witoldjnc.pricediffer.repository

import org.jsoup.nodes.Element
import ru.witoldjnc.pricediffer.dto.RoadmapCategory
import ru.witoldjnc.pricediffer.model.ErrorProductItem
import ru.witoldjnc.pricediffer.model.ProductItem

interface ShopFetcher {
    fun getPriceByCategory(roadmapCategory: RoadmapCategory): MutableList<ProductItem>
    fun parsePrice(itemElement: Element): String
    fun trimPrice(price: String): Double
    fun checkFaultUrls(categories: List<RoadmapCategory>): MutableList<ErrorProductItem>
}