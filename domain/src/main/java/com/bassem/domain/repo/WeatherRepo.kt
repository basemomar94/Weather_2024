package com.bassem.domain.repo

import com.bassem.domain.entity.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepo {

    suspend fun fetchWeatherData(location: String): Flow<WeatherResponse>
}