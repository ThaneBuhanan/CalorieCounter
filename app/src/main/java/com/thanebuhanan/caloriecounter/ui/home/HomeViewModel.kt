package com.thanebuhanan.caloriecounter.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanebuhanan.caloriecounter.data.dto.DayAndFoods
import com.thanebuhanan.caloriecounter.data.dto.DayDTO
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val nutritionDAO: NutritionDao) : ViewModel() {
    val goalCalories = MutableLiveData<Int>()
    val goalProtein = MutableLiveData<Int>()
    val days = MutableLiveData<List<DayDTO>>()
    val goToDayScreen = MutableLiveData<String?>()
    val staticUrl = MutableLiveData<String>("https://static.vecteezy.com/system/resources/thumbnails/000/539/724/small/dumbbell_2__28b_w_29.jpg")

    init {
        viewModelScope.launch {
            val justDays: List<DayDTO> = nutritionDAO.getAll()
                .justDays()
                .sortedByDescending { it.id }
            days.value = justDays

            val userDTO = nutritionDAO.getUser().first()
            goalProtein.value = userDTO.goalProtein
            goalCalories.value = userDTO.goalCalories
        }
    }

    fun createDay() {
        viewModelScope.launch {
            val todaysDate = getTodaysDate()
            var dayAndFoods = nutritionDAO.getByDayId(todaysDate)
            if (dayAndFoods == null) {
                val dayDTO = DayDTO(todaysDate)
                nutritionDAO.saveDay(dayDTO)
                dayAndFoods = nutritionDAO.getByDayId(todaysDate)
            }
            goToDayScreen.value = dayAndFoods.day.id
        }
    }

    fun doneNavigating() {
        goToDayScreen.value = null
    }

    fun onDayClicked(dayId: String) {
        goToDayScreen.value = dayId
    }
}

private fun List<DayAndFoods>.justDays(): List<DayDTO> {
    return map { it.day }
}

private fun getTodaysDate(): String {
    val c: Date = Calendar.getInstance().time
    val df = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())

    return df.format(c)
}
