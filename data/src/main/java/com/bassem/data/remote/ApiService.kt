package com.bassem.data.remote
import com.bassem.domain.entity.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("v2/top-headlines")
    suspend fun getCurrentWeather(
        @Query("sources") sources: String? = null,
        @Query("apiKey")
        apiKey: String = "api_key",
    ): Flow<WeatherResponse>

}