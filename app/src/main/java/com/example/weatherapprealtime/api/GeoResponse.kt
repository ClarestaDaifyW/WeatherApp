package com.example.weatherapprealtime.api

data class GeoResponse(
    val name: String?,
    val local_names: Map<String, String>?,
    val lat: Double?,
    val lon: Double?,
    val country: String?
)
