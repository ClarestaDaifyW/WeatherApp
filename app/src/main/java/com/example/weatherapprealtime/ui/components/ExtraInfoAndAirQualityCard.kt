package com.example.weatherapprealtime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.weatherapprealtime.api.AirQualityResponse
import com.example.weatherapprealtime.api.CurrentWeatherResponse
import com.example.weatherapprealtime.R

@Composable
fun ExtraInfoAndAirQualityCard(
    current: CurrentWeatherResponse?,
    air: AirQualityResponse?
) {
    if (current == null) return

    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if (darkTheme) Color(0xFF1E1E1E).copy(alpha = 0.7f) else Color.White.copy(alpha = 0.3f)
    val textColor = if (darkTheme) Color.White else Color.Black
    val subTextColor = if (darkTheme) Color.White.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.6f)
    val iconTint = if (darkTheme) Color(0xFF9F86FF) else Color(0xFF6C63FF)

    val list = buildAirQualityList(current, air)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {

        Column {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_airquality),
                    contentDescription = null,
                    tint = iconTint
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.air_quality),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = textColor
                )
            }

            Spacer(Modifier.height(20.dp))

            Column {
                for (i in list.chunked(3)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        i.forEach { item ->
                            AQItem(
                                icon = item.icon,
                                title = item.title,
                                value = item.value,
                                textColor = textColor,
                                subTextColor = subTextColor,
                                iconTint = iconTint
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun AQItem(
    icon: Int,
    title: Int,
    value: String,
    textColor: Color,
    subTextColor: Color,
    iconTint: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(90.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = title),
            fontSize = 11.sp,
            color = subTextColor
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}
