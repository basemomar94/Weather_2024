package com.bassem.data.repo

import com.bassem.core.entity.ApiResult
import com.bassem.core.utils.Logger
import com.bassem.data.remote.ApiService
import com.bassem.data.utils.getExceptionMessage
import com.bassem.domain.repo.WeatherRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepoImp @Inject constructor(private val apiService: ApiService) : WeatherRepo {
    private val log = Logger("WeatherRepoImp")
    override suspend fun fetchWeatherData(lat: String, lon: String) = flow {
        emit(ApiResult.Loading)
        try {
            emit(ApiResult.Success(apiService.getCurrentWeather(lat = lat, lon = lon)))
        } catch (e: Exception) {
            log.e("error is ${e.message}")
            emit(ApiResult.Fail(e.getExceptionMessage()))
        }
    }
}