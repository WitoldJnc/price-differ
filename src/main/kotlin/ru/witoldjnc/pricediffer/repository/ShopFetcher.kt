package ru.witoldjnc.pricediffer.repository

import ru.witoldjnc.pricediffer.dto.ProductItem
import ru.witoldjnc.pricediffer.dto.RoadmapProduct

interface ShopFetcher {
    fun fetchPriceFromItem(item: RoadmapProduct): Double
    fun enrichPriceFromRoadmapItems(): List<ProductItem>
}