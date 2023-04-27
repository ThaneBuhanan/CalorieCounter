package com.thanebuhanan.caloriecounter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thanebuhanan.caloriecounter.data.dto.DayAndFoods
import com.thanebuhanan.caloriecounter.data.dto.DayDTO
import com.thanebuhanan.caloriecounter.data.dto.FoodDTO

@Dao
interface NutritionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDay(dayDTO: DayDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFood(foodDTO: FoodDTO)

    @Query("SELECT * FROM day")
    suspend fun getAll(): List<DayAndFoods>

    @Query("SELECT * FROM day WHERE id = :id")
    suspend fun getByDayId(id: String): DayAndFoods
}
