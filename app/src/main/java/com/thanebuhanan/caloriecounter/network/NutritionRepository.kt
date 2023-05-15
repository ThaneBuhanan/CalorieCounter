package com.thanebuhanan.caloriecounter.network

import com.squareup.anvil.annotations.ContributesBinding
import com.thanebuhanan.caloriecounter.di.AppScope
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasFoodItem
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasResponse
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasService
import com.thanebuhanan.caloriecounter.network.nutritionix.FoodFields
import com.thanebuhanan.caloriecounter.network.nutritionix.FoodItem
import javax.inject.Inject
import javax.inject.Singleton

interface NutritionRepository {
    suspend fun getFoodItems(query: String): List<FoodItem>
}

@Singleton
@ContributesBinding(AppScope::class)
class NutritionRepositoryImpl @Inject constructor(
    private val calorieNinjasService: CalorieNinjasService,
) : NutritionRepository {
    override suspend fun getFoodItems(query: String): List<FoodItem> {
        val calorieNinjasResponse: CalorieNinjasResponse =
            calorieNinjasService.getFoodItems(
                "JwSEOs7a80TOnGOYvBMHgQ==LYQk4w7Sqx2bsJ5a",
                query,
            )
        val calorieNinjasFoodItems = calorieNinjasResponse.items
        return calorieNinjasFoodItems.toFoodItems()
    }
}

private fun List<CalorieNinjasFoodItem>.toFoodItems(): List<FoodItem> {
    return map {
        FoodItem(
            FoodFields(
                protein = it.protein,
                calories = it.calories,
                name = it.name,
            )
        )
    }
}