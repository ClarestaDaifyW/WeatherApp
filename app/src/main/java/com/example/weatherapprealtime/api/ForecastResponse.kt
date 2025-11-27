package com.example.weatherapprealtime.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastResponse(
    val cod: String?,
    val message: Int?,
    val cnt: Int?,
    val list: List<ForecastItem>?
) : Parcelable

@Parcelize
data class ForecastItem(
    val dt: Long?,
    val dt_txt: String?,
    val main: ForecastMain?,
    val weather: List<WeatherDesc>?,
    val wind: Wind?,
    val clouds: Clouds?
) : Parcelable

@Parcelize
data class ForecastMain(
    val temp: Double?,
    val feels_like: Double?,
    val pressure: Int?,
    val humidity: Int?
) : Parcelable

@Parcelize
data class WeatherDesc(
    val description: String?,
    val icon: String?
) : Parcelable

@Parcelize
data class Wind(
    val speed: Double?
) : Parcelable

@Parcelize
data class Clouds(
    val all: Int?
) : Parcelable
