package ru.witoldjnc.pricediffer.dto

class Product {
    var name: String = ""
    var description: String = ""
    var url: String = ""
    var price: Double = 0.0

    constructor()

    constructor(roadmapProduct: RoadmapProduct) {
        name = roadmapProduct.name
        description = roadmapProduct.description
        url = roadmapProduct.url
        price = roadmapProduct.price
    }


}