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

    fun saveUser(userDTO: UserDTO) {
        viewModelScope.launch {
            nutritionDAO.saveUser(userDTO)
        }
    }

}