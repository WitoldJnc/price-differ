package ru.witoldjnc.pricediffer.repository

import ru.witoldjnc.pricediffer.model.ProductItem
import ru.witoldjnc.pricediffer.dto.RoadmapProduct
import ru.witoldjnc.pricediffer.model.ErrorAddressProductList

interface ShopFetcher {
    fun fetchPriceFromItem(item: RoadmapProduct): Double
    fun enrichPriceFromRoadmapItems(roadmapProducts: List<RoadmapProduct>): List<ProductItem>
}