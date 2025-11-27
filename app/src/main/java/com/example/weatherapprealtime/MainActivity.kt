package com.example.weatherapprealtime

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weatherapprealtime.viewmodel.WeatherViewModel
import com.google.android.gms.location.LocationServices
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapprealtime.ui.screens.DetailWeatherScreen
import com.example.weatherapprealtime.ui.screens.HomeScreen
import com.example.weatherapprealtime.api.ForecastItem
import com.google.gson.Gson
import com.example.weatherapprealtime.ui.theme.WeatherAppRealTimeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) fetchLastLocationAndLoad()
            else viewModel.loadWeather(-6.200000, 106.816666) // fallback
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppRealTimeTheme {
                AppNavigation(viewModel)
            }
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fetchLastLocationAndLoad()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun fetchLastLocationAndLoad() {
        val fusedClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) return

        fusedClient.lastLocation
            .addOnSuccessListener { location ->
                val lat = location?.latitude ?: -6.2
                val lon = location?.longitude ?: 106.816
                viewModel.loadWeather(lat, lon)
            }
            .addOnFailureListener {
                viewModel.loadWeather(-6.2, 106.816)
            }
    }
}

@Composable
fun AppNavigation(viewModel: WeatherViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(viewModel = viewModel) // Hanya kirim viewModel
        }

        composable("detail/{data}") { backStackEntry ->
            val json = backStackEntry.arguments?.getString("data")
            val item = Gson().fromJson(json, ForecastItem::class.java)
            DetailWeatherScreen(item)
        }
    }
}

