package ru.witoldjnc.pricediffer.model

import ru.witoldjnc.pricediffer.dto.RoadmapProductItem

class ErrorProductItem {
    var name: String = ""
    var itemId: Int = 0
    var category: String = ""

    constructor(roadmapProduct: RoadmapProductItem) {
        name = roadmapProduct.name
        itemId = roadmapProduct.itemId
    }
}