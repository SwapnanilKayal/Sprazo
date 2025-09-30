package com.swapnanilkayal.sprazo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.swapnanilkayal.sprazo.R


val Kanit = FontFamily(
    Font(R.font.kanit_thin, FontWeight.Thin),
    Font(R.font.kanit_thinitalic, FontWeight.Thin, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_extralight, FontWeight.ExtraLight),
    Font(R.font.kanit_extralightitalic, FontWeight.ExtraLight, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_light, FontWeight.Light),
    Font(R.font.kanit_lightitalic, FontWeight.Light, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_regular, FontWeight.Normal),
    Font(R.font.kanit_italic, FontWeight.Normal, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_medium, FontWeight.Medium),
    Font(R.font.kanit_mediumitalic, FontWeight.Medium, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_semibold, FontWeight.SemiBold),
    Font(R.font.kanit_semibolditalic, FontWeight.SemiBold, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_bold, FontWeight.Bold),
    Font(R.font.kanit_bolditalic, FontWeight.Bold, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_extrabold, FontWeight.ExtraBold),
    Font(R.font.kanit_extrabolditalic, FontWeight.ExtraBold, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.kanit_black, FontWeight.Black),
    Font(R.font.kanit_blackitalic, FontWeight.Black, style = androidx.compose.ui.text.font.FontStyle.Italic),
)


val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Kanit,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    )
)
