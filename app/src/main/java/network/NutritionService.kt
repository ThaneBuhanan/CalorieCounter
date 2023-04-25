package network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://nutritionix-api.p.rapidapi.com/v1_1/search/"

interface NutritionService {
    //https://nutritionix-api.p.rapidapi.com/v1_1/search/cheddar/?fields=item_name,item_id,brand_name,nf_calories,nf_total_fat,nf_protein

    @GET("/v1_1/search/{query}")
    suspend fun getFoodItems(
        @Header("X-RapidAPI-KEY") apiKey: String,
        @Path("query") query: String,
        @Query("fields") fields: String,
    ): FoodResponse
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val moshiRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object Network {
    val nutritionService: NutritionService by lazy {
        moshiRetrofit.create(NutritionService::class.java)
    }
}