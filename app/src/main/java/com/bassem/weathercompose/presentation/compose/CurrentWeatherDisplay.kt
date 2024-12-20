package com.bassem.weathercompose.presentation.compose

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bassem.domain.entity.WeatherResponse
import com.bassem.weathercompose.R
import com.bassem.weathercompose.utils.formatTime
import java.util.Locale

@Composable
fun CurrentWeatherDisplay(weatherData: WeatherResponse, modifier: Modifier = Modifier) {
    val temperatureC = (weatherData.main.temp - 273.15).toInt()
    val feelsLikeC = (weatherData.main.feels_like - 273.15).toInt()
    val weatherDescription = weatherData.weather.firstOrNull()?.description ?: "No data"
    val weatherIconUrl = weatherData.weather.firstOrNull()?.let {
        "https://openweathermap.org/img/wn/${it.icon}.png"
    } ?: ""
    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.default_padding))
            .scrollable(state = scroll, orientation = Orientation.Vertical),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TemperatureWithCityDisplay(
            cityName = weatherData.name,
            temperatureC = temperatureC,
            weatherDescription = weatherDescription,
            weatherIconUrl = weatherIconUrl,
            feelsLikeC = feelsLikeC
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherInfoItem(
                icon = Icons.Default.WaterDrop,
                label = stringResource(R.string.humidity),
                value = "${weatherData.main.humidity}%"
            )
            WeatherInfoItem(
                icon = Icons.Default.Brightness5,
                label = stringResource(R.string.pressrue),
                value = "${weatherData.main.pressure} mb"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherInfoItem(
                icon = Icons.Default.Air,
                label = stringResource(R.string.wind),
                value = "${weatherData.wind.speed} kph"
            )

            WeatherInfoItem(
                icon = Icons.Default.Visibility,
                label = stringResource(R.string.visibility),
                value = "${weatherData.visibility / 1000} km"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherInfoItem(
                icon = Icons.Default.WbSunny,
                label = stringResource(R.string.sunrise),
                value = formatTime(weatherData.sys.sunrise, weatherData.timezone)
            )
            WeatherInfoItem(
                icon = Icons.Default.WbSunny,
                label = stringResource(R.string.sunset),
                value = formatTime(weatherData.sys.sunset, weatherData.timezone)
            )
        }

    }
}






