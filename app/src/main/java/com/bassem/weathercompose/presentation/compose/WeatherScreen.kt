package com.bassem.weathercompose.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.core.entity.ApiResult
import com.bassem.weathercompose.viewmodels.WeatherViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bassem.domain.entity.Current

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val result = viewModel.weatherResponse?.collectAsState(ApiResult.Loading)?.value


    LaunchedEffect(Unit) {
        viewModel.fetchWeather("22.1", "12.2")
    }

    when (result) {
        is ApiResult.Fail -> {}
        ApiResult.Loading -> {}
        is ApiResult.Success -> {
            CurrentWeatherDisplay((result.data) as Current)
        }

        null -> {}
    }


}