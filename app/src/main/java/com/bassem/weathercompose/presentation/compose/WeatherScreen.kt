package com.bassem.weathercompose.presentation.compose

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.weathercompose.R
import com.bassem.weathercompose.utils.getErrorMessage
import com.bassem.weathercompose.utils.getLastKnownLocation
import com.bassem.weathercompose.viewmodels.WeatherScreenState
import com.bassem.weathercompose.viewmodels.WeatherViewModel
import com.google.android.gms.location.LocationServices

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel(), modifier: Modifier) {
    val context = LocalContext.current
    val state by viewModel.weatherState.collectAsState(initial = WeatherScreenState.Loading)
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var lat by remember { mutableStateOf<String?>(null) }
    var lon by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    val launcher = rememberLauncherForActivityResult(
        contract = RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            context.getLastKnownLocation(
                fusedLocationClient,
                onLocationRetrieved = { latitude, longitude ->
                    if (latitude != null && longitude != null) {
                        lat = latitude
                        lon = longitude
                        errorMessage = null
                    } else {
                        errorMessage = context.getString(R.string.cant_fetch_location)
                    }
                })
        } else {
            lat = null
            lon = null
            errorMessage = context.getString(R.string.location_permission_required)
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

    if (errorMessage != null) {
        ErrorTextCompose(errorMessage)
    } else {
        when (state) {
            is WeatherScreenState.Error -> {
                ErrorTextCompose(context.getErrorMessage((state as WeatherScreenState.Error).types))
            }

            WeatherScreenState.Loading -> {
                LoadingIndicator()
            }

            is WeatherScreenState.Data -> {
                val currentWeather = (state as WeatherScreenState.Data).apps
                CurrentWeatherDisplay(currentWeather, modifier)
            }
        }
    }


}


