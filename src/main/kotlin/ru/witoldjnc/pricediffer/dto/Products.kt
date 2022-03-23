package ru.witoldjnc.pricediffer.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "products")
class Products {
    @Id
    var id: String? = null;

    @Indexed(unique = true)
    var date = LocalDate.now()

    var currentCourse: Double = 0.0
    var items: List<ProductItem>? = null

    constructor()
}