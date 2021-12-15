package com.inaki.fruitmvvmapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FruitsItem(
    @Json(name = "family")
    val family: String,
    @Json(name = "genus")
    val genus: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "nutritions")
    val nutritions: Nutritions,
    @Json(name = "order")
    val order: String
)