package com.example.weatherapprealtime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapprealtime.api.*
import com.example.weatherapprealtime.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResult?>(null)
    val weatherData: StateFlow<WeatherResult?> = _weatherData

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _selectedForecast = MutableStateFlow<ForecastItem?>(null)
    val selectedForecast: StateFlow<ForecastItem?> = _selectedForecast

    fun selectForecast(item: ForecastItem) {
        _selectedForecast.value = item
    }

    fun loadWeatherByCity(cityName: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null

                val geoList = RetrofitInstance.api.searchLocation(
                    cityName,
                    apiKey = BuildConfig.OWM_API_KEY
                )

                if (geoList.isEmpty()) {
                    _error.value = "Lokasi tidak ditemukan"
                    _loading.value = false
                    return@launch
                }

                val first = geoList.first()
                val lat = first.lat ?: throw Exception("Latitude tidak ditemukan")
                val lon = first.lon ?: throw Exception("Longitude tidak ditemukan")

                loadWeather(lat, lon)
            } catch (e: Exception) {
                _error.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _loading.value = false
            }
        }
    }

    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val current = RetrofitInstance.api.getCurrentWeather(lat, lon, BuildConfig.OWM_API_KEY)
                val forecast = RetrofitInstance.api.getForecast(lat, lon, BuildConfig.OWM_API_KEY)
                val air = RetrofitInstance.api.getAirQuality(lat, lon, BuildConfig.OWM_API_KEY)

                _weatherData.value = WeatherResult(current, forecast, air)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
    // Saat load forecast, pilih forecast hari ini secara otomatis
    fun setTodayForecast() {
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val forecastList = weatherData.value?.forecast?.list ?: return
        val todayForecast = forecastList.firstOrNull { it.dt_txt?.startsWith(todayDate) == true }
        _selectedForecast.value = todayForecast
    }

// Panggil ini setelah loadWeatherByCity selesai atau setelah forecast diterima

}
