package com.bassem.weathercompose.presentation.compose

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.core.entity.ApiResult
import com.bassem.domain.entity.WeatherResponse
import com.bassem.weathercompose.utils.getErrorMessage
import com.bassem.weathercompose.utils.getLastKnownLocation
import com.bassem.weathercompose.viewmodels.WeatherViewModel
import com.google.android.gms.location.LocationServices

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel(), modifier: Modifier) {
    val context = LocalContext.current
    val result by viewModel.weatherResponse.collectAsState(initial = ApiResult.Loading)
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var lat by remember { mutableStateOf<String?>(null) }
    var lon by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
           context.getLastKnownLocation(fusedLocationClient, onLocationRetrieved = { latitude, longitude ->
                lat = latitude
                lon = longitude
            })
        } else {
            lat = null
            lon = null
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    LaunchedEffect(lat, lon) {
        if (lat != null && lon != null) {
            viewModel.fetchWeather(lat!!, lon!!)
        }
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
            CurrentWeatherDisplay(currentWeather, modifier)
        }
    }
}


