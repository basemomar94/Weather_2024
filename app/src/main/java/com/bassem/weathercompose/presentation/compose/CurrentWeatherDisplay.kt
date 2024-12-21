package com.bassem.weathercompose.presentation.compose

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.bassem.domain.entity.WeatherResponse
import com.bassem.weathercompose.R
import com.bassem.weathercompose.utils.convertTemperature
import com.bassem.weathercompose.utils.formatTime
import com.bassem.weathercompose.utils.getCelsius
import com.bassem.weathercompose.utils.getDescription
import com.bassem.weathercompose.utils.getIconUrl
import com.bassem.weathercompose.utils.getVisibility
import com.bassem.weathercompose.utils.saveBitmapToGallery
import com.bassem.weathercompose.utils.saveImageWithText

@Composable
fun CurrentWeatherDisplay(
    weatherData: WeatherResponse,
    modifier: Modifier = Modifier
) {
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    var isCelsius by remember { mutableStateOf(true) }

    val temperature = weatherData.getCelsius()
    val feelsLike = weatherData.getCelsius()
    val weatherDescription = weatherData.getDescription()
    val weatherIconUrl = weatherData.getIconUrl()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.default_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_padding))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ToggleTextButton(
                text = stringResource(R.string.celsius),
                isSelected = isCelsius,
                onClick = { isCelsius = true }
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.default_padding)))
            ToggleTextButton(
                text = stringResource(R.string.fahrenheit),
                isSelected = !isCelsius,
                onClick = { isCelsius = false }
            )
        }

        TemperatureWithCityDisplay(
            cityName = weatherData.name,
            temperature = temperature.convertTemperature(isCelsius),
            weatherDescription = weatherDescription,
            weatherIconUrl = weatherIconUrl,
            feelsLike = feelsLike.convertTemperature(isCelsius)
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
                label = stringResource(R.string.pressure),
                value = "${weatherData.main.pressure}" + stringResource(R.string.mb)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherInfoItem(
                icon = Icons.Default.Air,
                label = stringResource(R.string.wind),
                value = "${weatherData.wind.speed}" + stringResource(R.string.kph)
            )

            WeatherInfoItem(
                icon = Icons.Default.Visibility,
                label = stringResource(R.string.visibility),
                value = weatherData.getVisibility() + stringResource(R.string.km)
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

        CaptureImageHandler { bitmap ->
            capturedImage = bitmap
        }

        WeatherOnImageScreen(
            text = temperature.convertTemperature(isCelsius) + " " + weatherDescription,
            capturedImage = capturedImage
        )
        Button(
            onClick = {
                capturedImage?.let { image ->
                    val resultImage = image.saveImageWithText(
                        text = temperature.convertTemperature(isCelsius) + " " + weatherDescription
                    )

                    resultImage.saveBitmapToGallery(context)
                }
            },
            enabled = capturedImage != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save Image")
        }
    }
}












