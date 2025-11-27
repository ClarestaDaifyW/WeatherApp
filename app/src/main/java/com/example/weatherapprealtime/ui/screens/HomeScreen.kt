package com.example.weatherapprealtime.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapprealtime.R
import com.example.weatherapprealtime.api.*
import com.example.weatherapprealtime.ui.components.*
import com.example.weatherapprealtime.ui.theme.DarkGradient1
import com.example.weatherapprealtime.ui.theme.DarkGradient3
import com.example.weatherapprealtime.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(viewModel: WeatherViewModel) {
    val state = viewModel.weatherData.collectAsState().value
    val loading = viewModel.loading.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val selectedForecast = viewModel.selectedForecast.collectAsState().value

    val darkTheme = isSystemInDarkTheme()

    Column(
        Modifier
            .fillMaxSize()
            .background(
                if (darkTheme) Brush.verticalGradient(listOf(DarkGradient1, DarkGradient3))
                else Brush.verticalGradient(listOf(Color(0xFFF5F5F5), Color(0xFFF5F5F5)))
            )
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(40.dp))

        SearchBar(onSearch = { if (it.isNotBlank()) viewModel.loadWeatherByCity(it) })

        Spacer(Modifier.height(20.dp))

        LocationCard(city = state?.current?.name)

        Spacer(Modifier.height(35.dp))

        // -------------------------------
        // Forecast grouping & auto-select today
        // -------------------------------
        val groupedForecast = groupForecastByDay(state?.forecast?.list)
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val autoSelected = selectedForecast ?: groupedForecast.firstOrNull { it.dt_txt?.startsWith(todayDate) == true }

        LaunchedEffect(groupedForecast, selectedForecast) {
            if (selectedForecast == null && autoSelected != null) {
                viewModel.selectForecast(autoSelected)
            }
        }

        val displayCurrent: CurrentWeatherResponse? = autoSelected?.let { forecastToCurrent(it) } ?: state?.current

        when {
            loading -> Text("Memuat...", color = if (darkTheme) Color.White else Color.Black)
            error != null -> Text(error, color = Color.Red)
            displayCurrent != null -> WeatherCard(displayCurrent)
        }

        Spacer(Modifier.height(5.dp))

        ExtraInfoCard(displayCurrent, state?.airQuality)

        Spacer(Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.weekly_forecast),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (darkTheme) Color.White else Color.Black
        )

        Spacer(Modifier.height(22.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            itemsIndexed(groupedForecast.take(7)) { index, item ->
                val safeTemp = "${item.main?.temp?.toInt() ?: 0}°"
                val safeIcon = item.weather?.firstOrNull()?.icon ?: "01d"
                val dayName = convertToDayName(item.dt_txt)
                val dateShort = convertToShortDate(item.dt_txt)
                val safeAqi = state?.airQuality?.list?.firstOrNull()?.main?.aqi ?: 1

                val isSelected = autoSelected == item

                ForecastCard(
                    day = dayName,
                    dateShort = dateShort,
                    temp = safeTemp,
                    icon = safeIcon,
                    aqi = safeAqi,
                    isSelected = isSelected,
                    onClick = { viewModel.selectForecast(item) }
                )
            }
        }
    }
}

// ---------------------------
// Helper Functions
// ---------------------------
fun groupForecastByDay(items: List<ForecastItem>?): List<ForecastItem> =
    items?.groupBy { it.dt_txt?.substring(0, 10) }?.map { it.value.first() } ?: emptyList()

fun convertToDayName(dtTxt: String?): String = try {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = sdf.parse(dtTxt ?: return "--")
    SimpleDateFormat("EEE", Locale.getDefault()).format(date)
} catch (e: Exception) { "--" }

fun convertToShortDate(dtTxt: String?): String = try {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = sdf.parse(dtTxt ?: return "--")
    SimpleDateFormat("dd MMM", Locale.getDefault()).format(date)
} catch (e: Exception) { "--" }

fun forecastToCurrent(forecast: ForecastItem): CurrentWeatherResponse = CurrentWeatherResponse(
    name = "", // ForecastItem tidak punya name
    main = forecast.main?.let { m ->
        Main(
            temp = m.temp ?: 0.0,
            feels_like = m.feels_like ?: 0.0,
            humidity = m.humidity ?: 0,
            pressure = m.pressure?.toDouble() ?: 0.0
        )
    } ?: Main(0.0, 0.0, 0, 0.0),
    weather = forecast.weather?.map { w -> WeatherInfo(description = w.description ?: "", icon = w.icon ?: "01d") } ?: listOf(),
    wind = forecast.wind?.let { w -> Wind(speed = w.speed ?: 0.0) } ?: Wind(0.0),
    clouds = forecast.clouds?.let { c -> Clouds(all = c.all ?: 0) } ?: Clouds(0)
)
