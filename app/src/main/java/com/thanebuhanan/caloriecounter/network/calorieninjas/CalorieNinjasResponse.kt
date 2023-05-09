package com.thanebuhanan.caloriecounter.network.calorieninjas

import com.squareup.moshi.Json

data class CalorieNinjasResponse(
    val items: List<CalorieNinjasFoodItem>
)

data class CalorieNinjasFoodItem(
    val name: String,
    val calories: Int,
    @Json(name = "protein_g")
    val protein: Int,
)

//{
//    "items": [
//    {
//        "name": "tortilla",
//        "calories": 316,
//        "serving_size_g": 100,
//        "fat_total_g": 7.1,
//        "fat_saturated_g": 1.7,
//        "protein_g": 8.7,
//        "sodium_mg": 479,
//        "potassium_mg": 123,
//        "cholesterol_mg": 0,
//        "carbohydrates_total_g": 55.4,
//        "fiber_g": 3.3,
//        "sugar_g": 0
//    }
//    ]
//}