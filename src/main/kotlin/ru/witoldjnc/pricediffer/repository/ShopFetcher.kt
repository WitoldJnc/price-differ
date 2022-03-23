package ru.witoldjnc.pricediffer.repository

import ru.witoldjnc.pricediffer.dto.Product
import ru.witoldjnc.pricediffer.dto.RoadmapProduct

interface ShopFetcher {
    fun fetchPriceFromItem(item: RoadmapProduct): Double
    fun enrichPriceFromRoadmapItems(): List<RoadmapProduct>
}