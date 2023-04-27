package com.thanebuhanan.caloriecounter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thanebuhanan.caloriecounter.data.dto.DayDTO
import com.thanebuhanan.caloriecounter.data.dto.FoodDTO
import com.thanebuhanan.caloriecounter.data.dto.UserDTO

@Database(entities = [UserDTO::class, DayDTO::class, FoodDTO::class], version = 1, exportSchema = false)
abstract class NutritionDatabase : RoomDatabase() {
    abstract fun getNutritionDao(): NutritionDao
}