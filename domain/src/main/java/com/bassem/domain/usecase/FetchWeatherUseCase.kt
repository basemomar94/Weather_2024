package com.bassem.domain.usecase

import com.bassem.domain.repo.WeatherRepo
import javax.inject.Inject

class FetchWeatherUseCase @Inject constructor(private val weatherRepo: WeatherRepo) {

    suspend operator fun invoke(lat: String, lon: String) =
        weatherRepo.fetchWeatherData(lat = lat, lon = lon)
}