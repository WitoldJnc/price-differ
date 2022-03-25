package ru.witoldjnc.pricediffer.dto

import com.google.gson.annotations.SerializedName

data class RoadmapCategory(
        @SerializedName("categoryId") var categoryId: Int,
        @SerializedName("name") var name: String,
        @SerializedName("baseUrl") var baseUrl: String,
        @SerializedName("items") var items: MutableList<RoadmapProductItem>
)