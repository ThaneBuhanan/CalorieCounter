package com.thanebuhanan.caloriecounter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.squareup.anvil.annotations.ContributesTo
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import com.thanebuhanan.caloriecounter.data.local.NutritionDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@ContributesTo(AppScope::class)
@Module
interface AppModule {

    companion object {
        @Provides
        fun providesContext(application: Application): Context {
            return application
        }

        @Provides
        @Singleton
        fun providesNutritionDao(context: Context): NutritionDao {
            return Room.databaseBuilder(
                context.applicationContext,
                NutritionDatabase::class.java, "nutrition.db"
            ).build().getNutritionDao()
        }
    }
}