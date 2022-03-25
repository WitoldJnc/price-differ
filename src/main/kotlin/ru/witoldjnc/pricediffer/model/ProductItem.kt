package ru.witoldjnc.pricediffer.model

import ru.witoldjnc.pricediffer.dto.RoadmapProductItem

class ProductItem {
    var name: String = ""
    var url: String = ""
    var price: Double = 0.0
    var productId: Int = 0;

    constructor(roadmapProductItem: RoadmapProductItem) {
        name = roadmapProductItem.name
        url = roadmapProductItem.url
        price = roadmapProductItem.price
        productId = roadmapProductItem.itemId
    }

    constructor()
}