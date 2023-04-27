package com.thanebuhanan.caloriecounter.network

import com.squareup.moshi.Json

data class FoodResponse(
    @Json(name = "hits")
    val foodItems: List<FoodItem>
)

data class FoodItem(
    val fields: FoodFields
)

data class FoodFields(
    @Json(name = "nf_protein")
    val protein: Double,
    @Json(name = "nf_calories")
    val calories: Double,
    @Json(name = "item_name")
    val name: String,
)

