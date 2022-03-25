package ru.witoldjnc.pricediffer.model

import ru.witoldjnc.pricediffer.dto.RoadmapProductItem

class ErrorProductItem {
    var name: String = ""
    var url: String = ""
    var itemId: Int = 0
    var category: String = ""

    constructor(roadmapProduct: RoadmapProductItem) {
        name = roadmapProduct.name
        url = roadmapProduct.url
        itemId = roadmapProduct.itemId
    }
}