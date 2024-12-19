package com.bassem.weathercompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.core.entity.ApiResult
import com.bassem.domain.usecase.FetchWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val useCase: FetchWeatherUseCase) : ViewModel() {
    private var _weatherResponse: MutableStateFlow<ApiResult<Any?>>? =
        MutableStateFlow(ApiResult.Loading)
    val weatherResponse: Flow<ApiResult<Any?>>? get() = _weatherResponse

    fun fetchWeather(lat: String, lon: String) = viewModelScope.launch {
        useCase(lat = lat, lon = lon).collect {
            if (it != null) {
                _weatherResponse?.value = it
            }

        }
    }
}