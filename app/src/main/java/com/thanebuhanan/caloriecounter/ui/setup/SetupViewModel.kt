package com.thanebuhanan.caloriecounter.ui.setup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanebuhanan.caloriecounter.data.dto.UserDTO
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetupViewModel @Inject constructor(private val nutritionDAO: NutritionDao) : ViewModel() {

    val weight = MutableLiveData<Int>()

    private val _navigateToHome = MutableLiveData<Unit>()
    val navigateToHome
        get() = _navigateToHome

    fun saveUser(hasGoalGain: Boolean) {
        val weight = weight.value ?: 0
        val userDTO = UserDTO(
            weight = weight,
            goalCalories = weight.toGoalCalories(hasGoalGain),
            goalProtein = weight.toGoalProtein(hasGoalGain),
            hasGoalGain = hasGoalGain
        )
        viewModelScope.launch {
            nutritionDAO.saveUser(userDTO)
            _navigateToHome.value = Unit
        }
    }
}

fun Int.toGoalCalories(hasGoalGain: Boolean): Int {
    val caloriesMaintainWeight = this * 15
    return caloriesMaintainWeight + when (hasGoalGain) {
        true -> 500
        else -> -500
    }
}

fun Int.toGoalProtein(hasGoalGain: Boolean): Int {
    val goalProtein = this * when (hasGoalGain) {
        true -> .8
        else -> .5
    }
    return goalProtein.toInt()
}