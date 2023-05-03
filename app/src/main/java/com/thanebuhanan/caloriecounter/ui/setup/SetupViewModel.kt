package com.thanebuhanan.caloriecounter.ui.setup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanebuhanan.caloriecounter.data.dto.UserDTO
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import kotlinx.coroutines.launch

class SetupViewModel(private val nutritionDAO: NutritionDao) : ViewModel() {

    val age = MutableLiveData<Int>()
    val heightFt = MutableLiveData<Int>()
    val heightIn = MutableLiveData<Int>()
    val weight = MutableLiveData<Int>()

    private val _navigateToHome = MutableLiveData<Unit>()
    val navigateToHome
        get() = _navigateToHome

    fun saveUser(hasGoalGain: Boolean) {
        val userDTO = UserDTO(
            age = age.value ?: 0,
            heightFt = heightFt.value ?: 0,
            heightIn = heightIn.value ?: 0,
            weight = weight.value ?: 0,
            hasGoalGain = hasGoalGain
        )
        viewModelScope.launch {
            nutritionDAO.saveUser(userDTO)
            _navigateToHome.value = Unit
        }
    }
}