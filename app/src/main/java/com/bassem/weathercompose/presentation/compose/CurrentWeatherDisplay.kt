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
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.bassem.domain.entity.Current
import com.bassem.weathercompose.R

@Composable
fun CurrentWeatherDisplay(current: Current) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.default_padding)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_padding))
        ) {
            // Header with Temperature
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${current.temp_c ?: "--"}°C",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                current.condition?.let {
                    Text(
                        text = it.text,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            // Secondary Details Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherInfoItem(
                    icon = Icons.Default.Air,
                    label = "Wind",
                    value = "${current.wind_kph ?: "--"} kph"
                )
                WeatherInfoItem(
                    icon = Icons.Default.WaterDrop,
                    label = "Humidity",
                    value = "${current.humidity ?: "--"}%"
                )
                WeatherInfoItem(
                    icon = Icons.Default.Brightness5,
                    label = "UV",
                    value = "${current.uv ?: "--"}"
                )
            }

            // Other Information
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherInfoItem(
                    icon = Icons.Default.WbCloudy,
                    label = "Feels Like",
                    value = "${current.feelslike_c ?: "--"}°C"
                )
                WeatherInfoItem(
                    icon = Icons.Default.Thermostat,
                    label = "Pressure",
                    value = "${current.pressure_mb ?: "--"} mb"
                )
                WeatherInfoItem(
                    icon = Icons.Default.Visibility,
                    label = "Visibility",
                    value = "${current.vis_km ?: "--"} km"
                )
            }

            // Last Updated
            current.last_updated?.let {
                Text(
                    text = "Last Updated: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
