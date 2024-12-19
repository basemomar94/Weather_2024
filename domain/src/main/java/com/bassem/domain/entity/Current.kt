package com.bassem.domain.entity

import com.bassem.domain.entity.AirQuality
import com.bassem.domain.entity.Condition

data class Current(
    val air_quality: AirQuality? = null,
    val cloud: Int? = null,
    val condition: Condition? = null,
    val feelslike_c: Int? = null,
    val feelslike_f: Double? = null,
    val gust_kph: Double? = null,
    val gust_mph: Double? = null,
    val humidity: Int? = null,
    val is_day: Int? = null,
    val last_updated: String? = null,
    val last_updated_epoch: Int? = null,
    val precip_in: Int? = null,
    val precip_mm: Double? = null,
    val pressure_in: Double? = null,
    val pressure_mb: Int? = null,
    val temp_c: Int? = null,
    val temp_f: Double? = null,
    val uv: Int? = null,
    val vis_km: Int? = null,
    val vis_miles: Int? = null,
    val wind_degree: Int? = null,
    val wind_dir: String? = null,
    val wind_kph: Double? = null,
    val wind_mph: Double? = null,
)