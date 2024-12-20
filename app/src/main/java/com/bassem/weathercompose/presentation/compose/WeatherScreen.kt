package com.bassem.weathercompose.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.core.entity.ApiResult
import com.bassem.domain.entity.WeatherResponse
import com.bassem.weathercompose.utils.getErrorMessage
import com.bassem.weathercompose.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel(), modifier: Modifier) {
    val context = LocalContext.current
    val result by viewModel.weatherResponse.collectAsState(initial = ApiResult.Loading)

    LaunchedEffect(Unit) {
        viewModel.fetchWeather("22.1", "12.2")
    }

    when (result) {
        is ApiResult.Fail -> {
            ErrorTextCompose(context.getErrorMessage((result as ApiResult.Fail).errorTypes))
        }

        ApiResult.Loading -> {
            LoadingIndicator()
        }

        is ApiResult.Success -> {
            val currentWeather = (result as ApiResult.Success<WeatherResponse>).data
            CurrentWeatherDisplay(currentWeather,modifier)
        }
    }
}
