package ru.witoldjnc.pricediffer.dto

import com.google.gson.annotations.SerializedName

data class RoadmapListProducts (
    @SerializedName("products") var product: List<RoadmapProduct>
)
