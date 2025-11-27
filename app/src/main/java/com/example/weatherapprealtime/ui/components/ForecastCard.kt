package com.example.weatherapprealtime.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapprealtime.ui.theme.getCardGradient
import com.example.weatherapprealtime.utils.getLocalWeatherIcon

@Composable
fun ForecastCard(
    day: String,
    dateShort: String,
    temp: String,
    icon: String,
    aqi: Int = 1,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val bgColor: Brush = getCardGradient(isSelected)
    val iconRes = getLocalWeatherIcon(icon)

    Column(
        modifier = Modifier
            .width(75.dp)
            .height(210.dp)
            .shadow(if (isSelected) 14.dp else 0.dp, RoundedCornerShape(55.dp))
            .background(brush = bgColor, shape = RoundedCornerShape(55.dp))
            .clickable { onClick?.invoke() }
            .padding(vertical = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            day,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))

        Text(
            dateShort,
            color = if (isSelected) Color.White.copy(alpha = 0.7f) else Color.Gray,
            fontSize = 12.sp
        )
        Spacer(Modifier.height(10.dp))

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
        Spacer(Modifier.height(10.dp))

        Text(
            temp,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .background(getAqiColor(aqi), RoundedCornerShape(10.dp))
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            Text("$aqi", color = Color.White, fontSize = 10.sp)
        }
    }
}

fun getAqiColor(aqi: Int): Color {
    return when (aqi) {
        1 -> Color(0xFF4CAF50)
        2 -> Color(0xFFFFC107)
        3 -> Color(0xFFFF9800)
        4 -> Color(0xFFF44336)
        5 -> Color(0xFF6A1B9A)
        else -> Color.Gray
    }
}
