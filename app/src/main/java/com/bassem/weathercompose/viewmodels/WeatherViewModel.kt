package com.bassem.weathercompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.core.entity.ApiResult
import com.bassem.core.entity.ErrorTypes
import com.bassem.domain.entity.WeatherResponse
import com.bassem.domain.usecase.FetchWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val useCase: FetchWeatherUseCase) : ViewModel() {
    private var _weatherState: MutableStateFlow<WeatherScreenState> =
        MutableStateFlow(WeatherScreenState.Loading)
    val weatherState: StateFlow<WeatherScreenState> get() = _weatherState

    fun fetchWeather(lat: String, lon: String) = viewModelScope.launch {
        useCase(lat = lat, lon = lon).collect { state ->
            _weatherState.value = when (state) {
                is ApiResult.Fail -> WeatherScreenState.Error(state.errorTypes)
                is ApiResult.Loading -> WeatherScreenState.Loading
                is ApiResult.Success -> WeatherScreenState.Data(state.data)
            }

        }
    }
}

sealed class WeatherScreenState {
    data object Loading : WeatherScreenState()
    data class Data(val apps: WeatherResponse) : WeatherScreenState()
    data class Error(val types: ErrorTypes?) : WeatherScreenState()
}