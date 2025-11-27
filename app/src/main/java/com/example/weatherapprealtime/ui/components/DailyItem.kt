package com.example.weatherapprealtime.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapprealtime.api.ForecastItem

@Composable
fun DailyItem(item: ForecastItem) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = item.dt_txt ?: "--",
            color = Color.White
        )

        AsyncImage(
            model = "https://openweathermap.org/img/wn/${item.weather?.first()?.icon}.png",
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )

        Text(
            text = "${item.main?.temp?.toInt() ?: 0}°",
            color = Color.White
        )
    }
}
