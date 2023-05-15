package com.thanebuhanan.caloriecounter.network.nutritionix

import com.squareup.moshi.Json

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

