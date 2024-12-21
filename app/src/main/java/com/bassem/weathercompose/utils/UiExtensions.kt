package com.bassem.weathercompose.utils

import android.content.Context
import com.bassem.core.entity.ErrorTypes
import com.bassem.domain.entity.Weather
import com.bassem.domain.entity.WeatherResponse
import com.bassem.weathercompose.R
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun Context.getErrorMessage(errorType: ErrorTypes) = when (errorType) {
    is ErrorTypes.Generic -> errorType.message
        ?: getString(R.string.unexpected_error)

    ErrorTypes.IoException -> getString(R.string.net_work_error)
    ErrorTypes.JsonException -> getString(R.string.local_parsing_error)
    ErrorTypes.SqlException -> getString(R.string.remote_parsing_error)
}

fun formatTime(timestamp: Int, timezoneOffset: Int): String {
    val localTime = Instant.ofEpochSecond(timestamp.toLong())
        .atZone(ZoneOffset.UTC)
        .plusSeconds(timezoneOffset.toLong())
    return DateTimeFormatter.ofPattern("hh:mm a").format(localTime)
}

fun WeatherResponse.getIconUrl() = weather.firstOrNull()?.let {
    "https://openweathermap.org/img/wn/${it.icon}.png"
} ?: ""

fun WeatherResponse.getDescription() = weather.firstOrNull()?.description ?: "No Data"

fun WeatherResponse.getCelsius() = (main.temp - 273.15)

fun WeatherResponse.getVisibility() = (main.pressure / 1000).toString()

fun Double.convertTemperature(isCelsius: Boolean): String {
    return if (isCelsius) {
        "%.1f°C".format(this)
    } else {
        "%.1f°F".format(this * 9 / 5 + 32)
    }
}