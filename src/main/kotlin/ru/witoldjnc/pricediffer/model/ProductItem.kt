package ru.witoldjnc.pricediffer.model

import ru.witoldjnc.pricediffer.dto.RoadmapProduct

class ProductItem {
    var name: String = ""
    var description: String = ""
    var url: String = ""
    var price: Double = 0.0
    var productMapId: Int = 0;

    constructor(roadmapProduct: RoadmapProduct) {
        name = roadmapProduct.name
        description = roadmapProduct.description
        url = roadmapProduct.url
        price = roadmapProduct.price
        productMapId = roadmapProduct.productMapId
    }

    constructor()
}