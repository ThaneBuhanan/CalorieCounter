package com.thanebuhanan.caloriecounter.network

import com.squareup.anvil.annotations.ContributesBinding
import com.thanebuhanan.caloriecounter.di.AppScope
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasResponse
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasService
import com.thanebuhanan.caloriecounter.network.calorieninjas.FoodItem
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
        return calorieNinjasResponse.items
    }
}