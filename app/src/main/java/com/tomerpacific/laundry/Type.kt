package com.tomerpacific.laundry

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Bangers = FontFamily(Font(R.font.bangers_regular, FontWeight.W400))

val LaundryTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Bangers,
        fontWeight = FontWeight.W400,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Bangers,
        fontWeight = FontWeight.W400,
        fontSize = 25.sp
    )
)
