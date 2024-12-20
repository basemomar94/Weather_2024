package com.bassem.weathercompose.utils

import android.content.Context
import com.bassem.core.entity.ErrorTypes
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