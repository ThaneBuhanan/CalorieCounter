package com.thanebuhanan.caloriecounter.data.dto

import androidx.room.Embedded
import androidx.room.Relation

data class DayAndFoods(
    @Embedded
    val day: DayDTO,
    @Relation(
        parentColumn = "id",
        entityColumn = "day"
    )
    val foods: List<FoodDTO>
)