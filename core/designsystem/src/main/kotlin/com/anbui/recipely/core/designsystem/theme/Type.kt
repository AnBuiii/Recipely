package com.anbui.recipely.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.anbui.recipely.core.designsystem.R

val sofia_pro = FontFamily(
    Font(R.font.sofia_pro_bold, FontWeight.Bold),
    Font(R.font.sofia_pro_medium, FontWeight.Medium),
    Font(R.font.sofia_pro_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.5.sp,
        color = ExtraDarkGrey
    ),
    headlineSmall = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp,
        color = DarkGreen
    ),
    titleLarge = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp,
        color = DarkGreen
    ),
    bodyLarge = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = ExtraDarkGrey
    ),
    bodySmall = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        color = ExtraDarkGrey

    ),
    labelLarge = TextStyle(
        fontFamily = sofia_pro,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = ExtraDarkGrey
    )

)
