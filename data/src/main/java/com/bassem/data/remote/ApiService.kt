package com.bassem.data.remote
import com.bassem.domain.entity.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String? = null,
        @Query("lon") lon: String? = null,
        @Query("appid")
        appid: String = "3bbbdb353c3e4d71a17dd1f6fda5a964",
    ): WeatherResponse

}