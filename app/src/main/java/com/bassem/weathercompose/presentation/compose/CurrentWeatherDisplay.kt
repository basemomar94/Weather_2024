package com.bassem.weathercompose.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.bassem.domain.entity.WeatherResponse
import com.bassem.weathercompose.R
import com.bassem.weathercompose.utils.formatTime
import java.util.Locale

@Composable
fun CurrentWeatherDisplay(weatherData: WeatherResponse, modifier: Modifier) {
    val temperatureC = (weatherData.main.temp - 273.15).toInt()
    val feelsLikeC = (weatherData.main.feels_like - 273.15).toInt()
    val weatherDescription = weatherData.weather.firstOrNull()?.description ?: "No data"
    val weatherIconUrl = weatherData.weather.firstOrNull()?.let {
        "https://openweathermap.org/img/wn/${it.icon}.png"
    } ?: ""

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.default_padding)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TemperatureWithCityDisplay(
            cityName = weatherData.name, temperatureC = temperatureC,
            weatherDescription = weatherDescription,
            weatherIconUrl = weatherIconUrl,
            feelsLikeC = feelsLikeC
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    weatherData.weather.firstOrNull()?.let { weather ->
                        Text(
                            text = weather.description.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherInfoItem(
                        icon = Icons.Default.Air,
                        label = "Wind",
                        value = "${weatherData.wind.speed} kph"
                    )
                    WeatherInfoItem(
                        icon = Icons.Default.WaterDrop,
                        label = "Humidity",
                        value = "${weatherData.main.humidity}%"
                    )
                    WeatherInfoItem(
                        icon = Icons.Default.Brightness5,
                        label = "Pressure",
                        value = "${weatherData.main.pressure} mb"
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherInfoItem(
                        icon = Icons.Default.WbCloudy,
                        label = "Feels Like",
                        value = "${(weatherData.main.feels_like - 273.15).toInt()}°C"
                    )
                    WeatherInfoItem(
                        icon = Icons.Default.Visibility,
                        label = "Visibility",
                        value = "${weatherData.visibility / 1000} km"
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherInfoItem(
                        icon = Icons.Default.WbSunny,
                        label = "Sunrise",
                        value = formatTime(weatherData.sys.sunrise, weatherData.timezone)
                    )
                    WeatherInfoItem(
                        icon = Icons.Default.WbSunny,
                        label = "Sunset",
                        value = formatTime(weatherData.sys.sunset, weatherData.timezone)
                    )
                }
            }
        }
    }
}




