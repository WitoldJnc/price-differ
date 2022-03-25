package ru.witoldjnc.pricediffer.dto

import com.google.gson.annotations.SerializedName

data class RoadmapProductItem(
        @SerializedName("itemId") var itemId: Int,
        @SerializedName("name") var name: String,
        @SerializedName("url") var url: String,
        var price: Double = 00.00
)