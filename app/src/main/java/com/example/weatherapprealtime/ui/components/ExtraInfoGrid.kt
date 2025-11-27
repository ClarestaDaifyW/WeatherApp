package com.example.weatherapprealtime.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapprealtime.R
import com.example.weatherapprealtime.api.CurrentWeatherResponse
import com.example.weatherapprealtime.api.AirQualityResponse

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExtraInfoCard(current: CurrentWeatherResponse?, air: AirQualityResponse?) {
    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if (darkTheme) Color(0xFF1E1E1E).copy(alpha = 0.7f) else Color.White.copy(alpha = 0.25f)
    val textColor = if (darkTheme) Color.White else Color.Black
    val subTextColor = if (darkTheme) Color.White.copy(alpha = 0.7f) else Color.Gray
    val iconTint = if (darkTheme) Color(0xFF9F86FF) else Color(0xFF6C63FF)

    val data = buildAirQualityList(current, air) // pastikan ini membaca state terbaru

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_air_quality_header),
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.air_quality),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                }
            }

            // Grid
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                data.forEach {
                    AirQualityInfo(
                        data = it,
                        modifier = Modifier.weight(1f),
                        textColor = textColor,
                        subTextColor = subTextColor,
                        iconTint = iconTint
                    )
                }
            }
        }
    }
}

@Composable
fun AirQualityInfo(
    modifier: Modifier = Modifier,
    data: AirQualityItem,
    textColor: Color,
    subTextColor: Color,
    iconTint: Color
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            painter = painterResource(data.icon),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(22.dp)
        )

        Column {
            Text(
                text = stringResource(id = data.title),
                fontSize = 12.sp,
                color = subTextColor
            )

            Text(
                text = data.value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}
