package com.thanebuhanan.caloriecounter.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanebuhanan.caloriecounter.data.dto.DayAndFoods
import com.thanebuhanan.caloriecounter.data.dto.DayDTO
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import kotlinx.coroutines.launch

class HomeViewModel(private val nutritionDAO: NutritionDao) : ViewModel() {

    val days = MutableLiveData<List<DayDTO>>()

    init {
        viewModelScope.launch {
            val justDays: List<DayDTO> = nutritionDAO.getAll().justDays()
            days.value = justDays
        }
    }
}


private fun List<DayAndFoods>.justDays(): List<DayDTO> {
    return map { it.day }
}