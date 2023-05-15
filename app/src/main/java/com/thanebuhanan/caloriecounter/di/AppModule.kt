package com.thanebuhanan.caloriecounter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thanebuhanan.caloriecounter.data.local.NutritionDao
import com.thanebuhanan.caloriecounter.data.local.NutritionDatabase
import com.thanebuhanan.caloriecounter.network.calorieninjas.CALORIE_NINJAS_BASE_URL
import com.thanebuhanan.caloriecounter.network.calorieninjas.CalorieNinjasService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

        @Provides
        @Singleton
        fun providesMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        fun providesRetrofitBuilder(moshi: Moshi): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
        }

        @Provides
        fun providesCalorieNinjasService(builder: Retrofit.Builder): CalorieNinjasService {
            val moshiRetrofit = builder
                .baseUrl(CALORIE_NINJAS_BASE_URL)
                .build()

            return moshiRetrofit.create(CalorieNinjasService::class.java)
        }
    }
}