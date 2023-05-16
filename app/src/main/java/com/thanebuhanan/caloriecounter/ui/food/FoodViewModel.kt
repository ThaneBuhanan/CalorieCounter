package com.thanebuhanan.caloriecounter.ui.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanebuhanan.caloriecounter.data.dto.FoodDTO
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import com.thanebuhanan.caloriecounter.network.NutritionRepository
import com.thanebuhanan.caloriecounter.network.calorieninjas.FoodItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private val nutritionDAO: NutritionDao,
    private val nutritionRepository: NutritionRepository,
) : ViewModel() {
    val foodItems = MutableLiveData<List<FoodItem>>()
    val goBack = MutableLiveData<Unit>()
    fun getFoodItems(query: String) {
        viewModelScope.launch {
            foodItems.value = nutritionRepository.getFoodItems(query)
        }
    }

    fun saveFoodItem(dayId: String, foodItem: FoodItem) {
        viewModelScope.launch {
            val foodDTO = FoodDTO(
                name = foodItem.name,
                calories = foodItem.calories,
                protein = foodItem.protein,
                day = dayId,
            )
            nutritionDAO.saveFood(foodDTO)
            goBack.value = Unit
        }
    }
}
