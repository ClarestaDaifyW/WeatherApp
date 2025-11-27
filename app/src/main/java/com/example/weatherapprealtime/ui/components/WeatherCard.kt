package com.example.weatherapprealtime.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.weatherapprealtime.api.CurrentWeatherResponse
import com.example.weatherapprealtime.R
import com.example.weatherapprealtime.ui.theme.getCardGradient
import com.example.weatherapprealtime.utils.getLocalWeatherIcon
import com.example.weatherapprealtime.utils.getWeatherDescription
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherCard(data: CurrentWeatherResponse?) {
    if (data == null) return

    val pattern = stringResource(id = R.string.today_date_format)
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val today = dateFormat.format(Date())

    val iconCode = data.weather?.firstOrNull()?.icon
    val iconRes = getLocalWeatherIcon(iconCode)

    val cardHeight = 157.dp
    val cardGradient: Brush = getCardGradient(isSelected = true)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .shadow(18.dp, RoundedCornerShape(45.dp))
                .background(brush = cardGradient, shape = RoundedCornerShape(45.dp))
                .padding(start = 35.dp, end = 35.dp, top = 25.dp, bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(Modifier.height(74.dp))

                    Text(
                        text = getWeatherDescription(iconCode),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = today,
                        color = Color.White.copy(alpha = 0.85f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${data.main?.temp?.toInt() ?: 0}°",
                        color = Color.White,
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = stringResource(
                            id = R.string.feels_like,
                            data.main?.feels_like?.toInt() ?: 0
                        ),
                        color = Color.White.copy(alpha = 0.85f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.TopStart)
                .padding(start = 30.dp)
                .offset(x = 0.dp, y = (-55).dp)
                .zIndex(10f)
        )
    }
}
