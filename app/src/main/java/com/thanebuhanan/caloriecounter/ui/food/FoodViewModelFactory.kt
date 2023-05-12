package com.thanebuhanan.caloriecounter.ui.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thanebuhanan.caloriecounter.data.local.NutritionDao

class FoodViewModelFactory(private val dataSource: NutritionDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}