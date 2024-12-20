package com.bassem.domain.repo

import com.bassem.core.entity.ApiResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepo {

    suspend fun fetchWeatherData(lat: String, lon: String): Flow<ApiResult<Any?>?>
}