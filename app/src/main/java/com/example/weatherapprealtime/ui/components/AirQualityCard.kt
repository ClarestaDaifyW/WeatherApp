package com.example.weatherapprealtime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapprealtime.api.AirQualityResponse

@Composable
fun AirQualityCard(data: AirQualityResponse?) {

    if (data?.list.isNullOrEmpty()) return

    val aqi = data!!.list!!.first().main?.aqi ?: 0

    val aqiStatus = when (aqi) {
        1 -> "Good 😊"
        2 -> "Fair 🙂"
        3 -> "Moderate 😐"
        4 -> "Poor 😷"
        5 -> "Very Poor 🚫"
        else -> "--"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.25f), RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Column {
            Text("Air Quality", style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Text("AQI: $aqi — $aqiStatus", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        }
    }
}
