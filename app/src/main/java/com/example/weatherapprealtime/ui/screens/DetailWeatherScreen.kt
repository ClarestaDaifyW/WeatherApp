package com.example.weatherapprealtime.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapprealtime.api.ForecastItem

@Composable
fun DetailWeatherScreen(item: ForecastItem) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF7C63FF), Color(0xFFC25AFF))
                )
            )
            .padding(20.dp)
    ) {
        Text("Forecast Detail", color = Color.White)

        Spacer(Modifier.height(10.dp))

        Text("Date: ${item.dt_txt ?: "-"}", color = Color.White)
        Text("Temp: ${item.main?.temp ?: "-"}°C", color = Color.White)
        Text("Wind: ${item.wind?.speed ?: "-"} m/s", color = Color.White)
        Text("Clouds: ${item.clouds?.all ?: "-"}%", color = Color.White)
    }
}
