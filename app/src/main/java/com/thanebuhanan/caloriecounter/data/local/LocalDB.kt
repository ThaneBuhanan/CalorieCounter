package com.thanebuhanan.caloriecounter.data.local

import android.content.Context
import androidx.room.Room

object LocalDB {
    private lateinit var dao: NutritionDao
    fun getNutritionDao(context: Context): NutritionDao {
        if (!::dao.isInitialized) {
            dao = Room.databaseBuilder(
                context.applicationContext,
                NutritionDatabase::class.java, "nutrition.db"
            ).build().getNutritionDao()
        }

        return dao
    }
}
