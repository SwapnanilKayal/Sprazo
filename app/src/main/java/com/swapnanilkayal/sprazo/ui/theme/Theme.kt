package com.swapnanilkayal.sprazo.ui.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat


private val LightThemeBackground = Color.White
private val LightThemeSurface = Color(0xFFF5F5F5)
private val LightThemePrimary = Color(0xFF212121)
private val LightThemeOnPrimary = Color.White
private val LightThemeSecondary = Color(0xFF757575)
private val LightThemeOnSecondary = Color.White
private val LightThemeTertiary = Color(0xFF616161)
private val LightThemeOnTertiary = Color.White
private val LightThemeOnBackground = Color(0xFF212121)
private val LightThemeOnSurface = Color(0xFF212121)
private val LightThemeSurfaceVariant = Color(0xFFE0E0E0)
private val LightThemeOnSurfaceVariant = Color(0xFF212121)
private val LightThemeOutline = Color(0xFFBDBDBD)

 
private val DarkThemeBackground = Color.Black
private val DarkThemeSurface = Color(0xFF121212)
private val DarkThemePrimary = Color(0xFFE0E0E0)
private val DarkThemeOnPrimary = Color.Black
private val DarkThemeSecondary = Color(0xFFBDBDBD)
private val DarkThemeOnSecondary = Color.Black
private val DarkThemeTertiary = Color(0xFF9E9E9E)
private val DarkThemeOnTertiary = Color.Black
private val DarkThemeOnBackground = Color(0xFFE0E0E0)
private val DarkThemeOnSurface = Color(0xFFE0E0E0)
private val DarkThemeSurfaceVariant = Color(0xFF313131)
private val DarkThemeOnSurfaceVariant = Color(0xFFE0E0E0)
private val DarkThemeOutline = Color(0xFF616161)


private val DarkColorScheme = darkColorScheme(
primary = DarkThemePrimary,
onPrimary = DarkThemeOnPrimary,
secondary = DarkThemeSecondary,
onSecondary = DarkThemeOnSecondary,
tertiary = DarkThemeTertiary,
onTertiary = DarkThemeOnTertiary,
background = DarkThemeBackground,
onBackground = DarkThemeOnBackground,
surface = DarkThemeSurface,
onSurface = DarkThemeOnSurface,
surfaceVariant = DarkThemeSurfaceVariant,
onSurfaceVariant = DarkThemeOnSurfaceVariant,
outline = DarkThemeOutline,
error = Color(0xFFB00020),
onError = Color.White
)

private val LightColorScheme = lightColorScheme(
primary = LightThemePrimary,
onPrimary = LightThemeOnPrimary,
secondary = LightThemeSecondary,
onSecondary = LightThemeOnSecondary,
tertiary = LightThemeTertiary,
onTertiary = LightThemeOnTertiary,
background = LightThemeBackground,
onBackground = LightThemeOnBackground,
surface = LightThemeSurface,
onSurface = LightThemeOnSurface,
surfaceVariant = LightThemeSurfaceVariant,
onSurfaceVariant = LightThemeOnSurfaceVariant,
outline = LightThemeOutline,
error = Color(0xFFB00020),
onError = Color.White
)

fun ComponentActivity.setStatusBar(color: Int, darkIcons: Boolean) {
    window.statusBarColor = color
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = darkIcons
}

@Composable
fun SprazoTheme(

darkTheme: Boolean = isSystemInDarkTheme(),
 
 
dynamicColor: Boolean = true,
content: @Composable () -> Unit
) {

val colorScheme = when {
dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
val context = LocalContext.current
if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
}
darkTheme -> DarkColorScheme
else -> LightColorScheme
}

MaterialTheme(
colorScheme = colorScheme,
typography = AppTypography,
content = content
)
}
