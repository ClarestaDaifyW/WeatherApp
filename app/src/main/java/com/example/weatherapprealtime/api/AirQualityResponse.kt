package com.example.weatherapprealtime.api

data class AirQualityResponse(
    val list: List<AQData>?
)

data class AQData(
    val main: AQIndex?,
    val components: AQComponents?
)

data class AQIndex(
    val aqi: Int?
)

data class AQComponents(
    val co: Double?,
    val no: Double?,
    val no2: Double?,
    val o3: Double?,
    val so2: Double?,
    val pm2_5: Double?,
    val pm10: Double?,
    val nh3: Double?
)
