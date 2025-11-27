package com.example.weatherapprealtime.utils

import com.example.weatherapprealtime.R

fun getLocalWeatherIcon(iconCode: String?): Int {

    return when (iconCode) {

        // ☀️ CERAH
        "01d" -> R.drawable.img_sun
        "01n" -> R.drawable.img_moon_stars

        // 🌤 SEDIKIT BERAWAN
        "02d", "02n" -> R.drawable.img_cloudy

        // ☁️ BERAWAN
        "03d", "03n", "04d", "04n" -> R.drawable.img_clouds

        // 🌧 HUJAN RINGAN
        "09d", "09n" -> R.drawable.img_sub_rain

        // 🌧 HUJAN
        "10d", "10n" -> R.drawable.img_rain

        // ⛈ PETIR
        "11d", "11n" -> R.drawable.img_thunder

        // ❓ DEFAULT
        else -> R.drawable.img_clouds
    }
}
