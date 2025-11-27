package com.example.weatherapprealtime.api

data class CurrentWeatherResponse(
    val name: String?,
    val main: Main?,
    val wind: Wind?,
    val weather: List<WeatherInfo>?, // ⬅ update ini
    val clouds: Clouds?
)

data class Main(
    val temp: Double?,
    val feels_like: Double?,
    val humidity: Int?,
    val pressure: Double?
)

data class WeatherInfo(
    val description: String?,
    val icon: String?
)
