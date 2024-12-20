package com.bassem.weathercompose.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bassem.weathercompose.R

@Composable
fun WeatherInfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    iconSize: Dp = dimensionResource(R.dimen.icon_size),
    iconColor: Color = MaterialTheme.colorScheme.primary,
    labelColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(iconSize),
            tint = iconColor
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = labelColor
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = valueColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherInfoItemPreview() {
    WeatherInfoItem(icon = Icons.Default.Info, label = "test", value = "very hot")
}