package ru.witoldjnc.pricediffer.dto

import com.google.gson.annotations.SerializedName

data class RoadmapProduct  (
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("url") val url: String,
        var price: Double = 00.00
)