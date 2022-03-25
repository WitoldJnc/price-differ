package ru.witoldjnc.pricediffer.dto

import com.google.gson.annotations.SerializedName

data class RoadmapCategoryList(
        @SerializedName("categories") var product: MutableList<RoadmapCategory>
)
