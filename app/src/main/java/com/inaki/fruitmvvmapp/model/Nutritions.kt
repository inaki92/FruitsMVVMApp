package com.inaki.fruitmvvmapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Nutritions(
    @Json(name = "calories")
    val calories: Int,
    @Json(name = "carbohydrates")
    val carbohydrates: Double,
    @Json(name = "fat")
    val fat: Double,
    @Json(name = "protein")
    val protein: Double,
    @Json(name = "sugar")
    val sugar: Double
)