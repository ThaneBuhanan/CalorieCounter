package com.thanebuhanan.caloriecounter.network.calorieninjas

import com.thanebuhanan.caloriecounter.network.moshiRetrofitBuilder
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val CALORIE_NINJAS_BASE_URL = "https://api.calorieninjas.com/"

interface CalorieNinjasService {
    //https://api.calorieninjas.com/v1/nutrition?query=

    @GET ("v1/nutrition/")
    suspend fun getFoodItems(
        @Header("X-Api-Key") apiKey: String,
        @Query("query") query: String,
    ): CalorieNinjasResponse
}

object CalorieNinjasNetwork {
    val calorieNinjasService: CalorieNinjasService by lazy {
        val moshiRetrofit = moshiRetrofitBuilder
            .baseUrl(CALORIE_NINJAS_BASE_URL)
            .build()

        moshiRetrofit.create(CalorieNinjasService::class.java)
    }
}
