package com.bassem.domain.usecase

import com.bassem.domain.repo.WeatherRepo
import javax.inject.Inject

class FetchWeatherUseCase @Inject constructor(private val weatherRepo: WeatherRepo) {

    suspend operator fun invoke(location: String) = weatherRepo.fetchWeatherData(location)
}