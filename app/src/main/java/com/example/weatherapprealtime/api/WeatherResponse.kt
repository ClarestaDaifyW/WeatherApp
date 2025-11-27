package com.example.weatherapprealtime.api

import com.squareup.moshi.Json

data class WeatherResponse(
    val lat: Double?,
    val lon: Double?,
    val timezone: String?,
    val current: Current?,
    val daily: List<Daily>?
)

data class Current(
    val dt: Long?,
    val temp: Double?,
    val humidity: Int?,
    @Json(name = "wind_speed")
    val windSpeed: Double?,
    val weather: List<Weather>?
)

data class Daily(
    val dt: Long?,
    val temp: Temp?,
    val humidity: Int?,
    val weather: List<Weather>?
)

data class Temp(
    val day: Double?,
    val min: Double?,
    val max: Double?,
    val night: Double?
)

data class Weather(
    val main: String?,
    val description: String?,
    val icon: String?
)
