package com.bassem.data.repo

import com.bassem.data.remote.ApiService
import com.bassem.domain.entity.WeatherResponse
import com.bassem.domain.repo.WeatherRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepoImp @Inject constructor(private val apiService: ApiService) : WeatherRepo {
    override suspend fun fetchWeatherData(location: String): Flow<WeatherResponse> {
       return apiService.getCurrentWeather("")
    }
}