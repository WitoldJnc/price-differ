package ru.witoldjnc.pricediffer.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document(collection = "products")
class Products {
    @Id
    var id: String? = null;

    @Indexed(unique = true)
    @Field("date")
    var date = LocalDate.now()

    var currentCourse: Double = 0.0
    var items: MutableList<ProductItem> = mutableListOf()

    constructor()
}