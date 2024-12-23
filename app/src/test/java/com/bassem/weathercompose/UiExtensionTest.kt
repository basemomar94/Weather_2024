package com.bassem.weathercompose

import android.content.Context
import com.bassem.core.entity.ErrorTypes
import com.bassem.domain.entity.Weather
import com.bassem.domain.entity.WeatherResponse
import com.bassem.weathercompose.utils.convertTemperature
import com.bassem.weathercompose.utils.formatTime
import com.bassem.weathercompose.utils.getDescription
import com.bassem.weathercompose.utils.getErrorMessage
import com.bassem.weathercompose.utils.getIconUrl
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class FormatTimeTest(
    private val timestamp: Int,
    private val timezoneOffset: Int,
    private val expected: String
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(1672531200, 0, "12:00 AM"),
            arrayOf(1672534800, 3600, "02:00 AM")
        )
    }

    @Test
    fun `test formatTime`() {
        val result = formatTime(timestamp, timezoneOffset)
        assertEquals(expected, result)
    }
}

class WeatherUtilsTest {

    private val mockContext = mockk<Context>()

    @Test
    fun `test getErrorMessage Generic error`() {
        val error = ErrorTypes.Generic("Custom error")
        val result = mockContext.getErrorMessage(error)
        assertEquals("Custom error", result)
    }

    @Test
    fun `test getErrorMessage IoException`() {
        every { mockContext.getString(R.string.net_work_error) } returns "Network Error"
        val result = mockContext.getErrorMessage(ErrorTypes.IoException)
        assertEquals("Network Error", result)
    }

    @Test
    fun `test getErrorMessage JsonException`() {
        every { mockContext.getString(R.string.local_parsing_error) } returns "Parsing Error"
        val result = mockContext.getErrorMessage(ErrorTypes.JsonException)
        assertEquals("Parsing Error", result)
    }

    @Test
    fun `test getErrorMessage SqlException`() {
        every { mockContext.getString(R.string.remote_parsing_error) } returns "Remote Error"
        val result = mockContext.getErrorMessage(ErrorTypes.SqlException)
        assertEquals("Remote Error", result)
    }

    @Test
    fun `test WeatherResponse getIconUrl`() {
        val weatherResponse = WeatherResponse(
            weather = listOf(Weather(icon = "10d", description = "test")),
            main = mockk(relaxed = true),
            visibility = 0,
            name = "test",
            sys = mockk(relaxed = true),
            timezone = mockk(relaxed = true),
            wind = mockk(relaxed = true),
        )
        val result = weatherResponse.getIconUrl()
        assertEquals("https://openweathermap.org/img/wn/10d.png", result)
    }

    @Test
    fun `test WeatherResponse getDescription`() {
        val weatherResponse = WeatherResponse(
            weather = listOf(Weather(description = "Clear sky", icon = "")),
            main = mockk(),
            visibility = 0,
            name = "test",
            sys = mockk(relaxed = true),
            timezone = mockk(relaxed = true),
            wind = mockk(relaxed = true),
        )
        val result = weatherResponse.getDescription()
        assertEquals("Clear sky", result)
    }

    @Test
    fun `test convertTemperature to Celsius`() {
        val temperature = 300.0
        val result = temperature.convertTemperature(true)
        assertEquals("300.0°C", result)
    }

    @Test
    fun `test convertTemperature to Fahrenheit`() {
        val temperature = 0.0
        val result = temperature.convertTemperature(false)
        assertEquals("32.0°F", result)
    }
}
