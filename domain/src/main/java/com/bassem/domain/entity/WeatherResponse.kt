package com.bassem.domain.entity

import com.bassem.domain.entity.Current
import com.bassem.domain.entity.Location

data class WeatherResponse(
    val current: Current,
    val location: Location
)