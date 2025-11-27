package com.example.weatherapprealtime.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weatherapprealtime.R
import com.example.weatherapprealtime.api.CurrentWeatherResponse
import com.example.weatherapprealtime.api.AirQualityResponse

fun buildAirQualityList(
    current: CurrentWeatherResponse?,
    air: AirQualityResponse?
): List<AirQualityItem> {

    val comp = air?.list?.firstOrNull()?.components

    return listOf(
        AirQualityItem(
            icon = R.drawable.ic_realfeel,
            title = R.string.real_feel,
            value = "${current?.main?.feels_like?.toInt() ?: 0}°"
        ),
        AirQualityItem(
            icon = R.drawable.ic_wind,
            title = R.string.wind,
            value = "${current?.wind?.speed ?: 0} m/s"
        ),
        AirQualityItem(
            icon = R.drawable.ic_humidity,
            title = R.string.humidity,
            value = "${current?.main?.humidity ?: 0}%"
        ),
        AirQualityItem(
            icon = R.drawable.ic_rain,
            title = R.string.clouds,
            value = "${current?.clouds?.all ?: 0}%"
        ),
        AirQualityItem(
            icon = R.drawable.ic_so2,
            title = R.string.so2,
            value = "${comp?.so2 ?: 0}"
        ),
        AirQualityItem(
            icon = R.drawable.ic_o3,
            title = R.string.o3,
            value = "${comp?.o3 ?: 0}"
        )
    )
}
