package com.example.weatherapprealtime.ui.model

data class ForecastUI(
    val day: String,
    val date: String,
    val temp: String,
    val icon: String,
    val aqi: Int,
    val isSelected: Boolean = false
)
