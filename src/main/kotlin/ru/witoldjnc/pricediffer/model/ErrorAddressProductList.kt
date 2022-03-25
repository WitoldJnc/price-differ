package ru.witoldjnc.pricediffer.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document(collection = "error_fetching")
class ErrorAddressProductList {
    @Id
    var id: String? = null;

    @Indexed(unique = true)
    @Field("date")
    var date = LocalDate.now()

    var items: MutableList<ErrorProductItem> = mutableListOf()

    constructor()

    constructor(errorsList: MutableList<ErrorProductItem>){
        items = errorsList
    }

}