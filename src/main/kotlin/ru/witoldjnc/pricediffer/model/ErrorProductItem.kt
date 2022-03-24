package ru.witoldjnc.pricediffer.model

import ru.witoldjnc.pricediffer.dto.RoadmapProduct

class ErrorProductItem {
    var name: String = ""
    var url: String = ""

    constructor(roadmapProduct: RoadmapProduct){
        name = roadmapProduct.name
        url = roadmapProduct.url
    }
}