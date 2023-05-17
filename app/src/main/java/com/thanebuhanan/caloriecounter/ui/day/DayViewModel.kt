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
    val protein = MutableLiveData<String>()
    val calories = MutableLiveData<String>()

    fun populateScreen(dayId: String) {
        viewModelScope.launch {
            val dayAndFoods: DayAndFoods = nutritionDAO.getByDayId(dayId)
            val items = dayAndFoods.foods.toFoodItems()

            protein.value = items.sumOf { it.protein }.toString()
            calories.value = items.sumOf { it.calories }.toString()
            foodItems.value = items
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