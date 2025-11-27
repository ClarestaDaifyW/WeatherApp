package com.example.weatherapprealtime.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weatherapprealtime.R

@Composable
fun getWeatherDescription(icon: String?): String {
    return when (icon) {

        // ☀️ CERAH
        "01d", "01n" -> stringResource(R.string.weather_clear_sky)

        // 🌤 SEDIKIT BERAWAN
        "02d", "02n" -> stringResource(R.string.weather_few_clouds)

        // ☁️ BERAWAN / MENDUNG
        "03d", "03n", "04d", "04n" -> stringResource(R.string.weather_clouds)

        // 🌧 HUJAN RINGAN
        "09d", "09n" -> stringResource(R.string.weather_light_rain)

        // 🌧 HUJAN
        "10d", "10n" -> stringResource(R.string.weather_rain)

        // ⛈ PETIR
        "11d", "11n" -> stringResource(R.string.weather_thunderstorm)

        // DEFAULT
        else -> stringResource(R.string.weather_clouds)
    }
}
