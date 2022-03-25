package ru.witoldjnc.pricediffer.dto

import com.google.gson.annotations.SerializedName

data class RoadmapProductItem(
        @SerializedName("id") var itemId: Int,
        @SerializedName("name") var name: String,
        var price: Double = 00.00
)