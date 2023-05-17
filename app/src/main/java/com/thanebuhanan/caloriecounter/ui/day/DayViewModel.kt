package com.thanebuhanan.caloriecounter.ui.day

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanebuhanan.caloriecounter.data.dto.DayAndFoods
import com.thanebuhanan.caloriecounter.data.dto.FoodDTO
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import com.thanebuhanan.caloriecounter.network.calorieninjas.FoodItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayViewModel @Inject constructor(
    private val nutritionDAO: NutritionDao,
) : ViewModel() {
    val foodItems = MutableLiveData<List<FoodItem>>()

    fun populateFoodItems(dayId: String) {
        viewModelScope.launch {
            val dayAndFoods: DayAndFoods = nutritionDAO.getByDayId(dayId)
            foodItems.value = dayAndFoods.foods.toFoodItems()
        }
    }
}

private fun List<FoodDTO>.toFoodItems(): List<FoodItem> {
    return map {
        FoodItem(
            name = it.name,
            calories = it.calories,
            protein = it.protein,
        )
    }
}