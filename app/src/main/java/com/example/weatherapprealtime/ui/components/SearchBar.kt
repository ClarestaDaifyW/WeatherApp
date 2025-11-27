package com.example.weatherapprealtime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.weatherapprealtime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val darkTheme = isSystemInDarkTheme()

    // Background & warna teks sesuai tema
    val backgroundColor = if (darkTheme) Color(0x33FFFFFF) else Color(0xFFE0E0E0) // lebih kontras di terang
    val placeholderColor = if (darkTheme) Color.White.copy(alpha = 0.7f) else Color.Gray
    val textColor = if (darkTheme) Color.White else Color.Black
    val cursorColor = textColor

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_location),
                fontWeight = FontWeight.Medium,
                color = placeholderColor
            )
        }
        ,
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = placeholderColor
            )
        },
        textStyle = TextStyle(
            color = textColor,
            fontWeight = FontWeight.Medium
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (text.isNotBlank()) onSearch(text.trim())
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                backgroundColor,
                RoundedCornerShape(28.dp)
            )
            .padding(vertical = 3.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            cursorColor = cursorColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
