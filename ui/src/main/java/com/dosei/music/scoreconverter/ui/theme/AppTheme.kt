package com.dosei.music.scoreconverter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xff283593)
val PrimaryColorDark = Color(0xff001064)

val SecondaryColor = Color(0xff424242)
val SecondaryColorDark = Color(0xff1b1b1b)

val ErrorColor = Color(0xffc62828)

val LightColors = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryColorDark,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryColorDark,
    background = Color.White,
    surface = Color.White,
    error = ErrorColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

val DarkColors = darkColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryColorDark,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryColorDark,
    error = ErrorColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onError = Color.White
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}