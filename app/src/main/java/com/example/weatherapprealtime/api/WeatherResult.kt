package com.example.weatherapprealtime.api

data class WeatherResult(
    val current: CurrentWeatherResponse,
    val forecast: ForecastResponse,
    val airQuality: AirQualityResponse
)
