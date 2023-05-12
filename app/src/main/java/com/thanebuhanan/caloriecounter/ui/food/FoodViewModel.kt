package com.thanebuhanan.caloriecounter.ui.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasFoodItem
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasNetwork
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasResponse
import com.thanebuhanan.caloriecounter.network.nutritionix.FoodFields
import com.thanebuhanan.caloriecounter.network.nutritionix.FoodItem
import kotlinx.coroutines.launch

class FoodViewModel(nutritionDAO: NutritionDao) : ViewModel() {
    val foodItems = MutableLiveData<List<FoodItem>>()

    fun getFoodItems(query: String) {
        viewModelScope.launch {
            val calorieNinjasResponse: CalorieNinjasResponse =
                CalorieNinjasNetwork.calorieNinjasService.getFoodItems(
                    "JwSEOs7a80TOnGOYvBMHgQ==LYQk4w7Sqx2bsJ5a",
                    query
                )
            val calorieNinjasFoodItems = calorieNinjasResponse.items
            foodItems.value = calorieNinjasFoodItems.toFoodItems()
        }
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