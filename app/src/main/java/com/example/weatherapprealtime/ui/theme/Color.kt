package com.example.weatherapprealtime.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// -----------------------------
// Warna utama
// -----------------------------
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// -----------------------------
// Gradient untuk Card
// -----------------------------
val ColorGradient1 = Color(0xFFe262e6)
val ColorGradient2 = Color(0xFF9f62ea)
val ColorGradient3 = Color(0xFF5264f0)

val DarkGradient1 = Color(0xFF1E1E2C)
val DarkGradient2 = Color(0xFF3C3C5A)
val DarkGradient3 = Color(0xFF5A5A7A)

// -----------------------------
// Fungsi untuk mendapatkan gradient card
// -----------------------------
@Composable
fun getCardGradient(isSelected: Boolean = false): Brush {
    val darkTheme = isSystemInDarkTheme()
    return if (isSelected) {
        if (darkTheme) {
            Brush.verticalGradient(
                colors = listOf(DarkGradient1, DarkGradient2)
            )
        } else {
            Brush.verticalGradient(
                colors = listOf(ColorGradient1, ColorGradient2)
            )
        }
    } else {
        if (darkTheme) {
            Brush.verticalGradient(
                colors = listOf(DarkGradient2, DarkGradient3)
            )
        } else {
            Brush.verticalGradient(
                colors = listOf(Color(0xFFF5F5F5), Color(0xFFF5F5F5))
            )
        }
    }
}
