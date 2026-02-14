package com.tomerpacific.laundry.model

import androidx.annotation.DrawableRes

data class LaundrySymbol(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val drawableId: Int,
    val temperature: Int? = null,
    val descriptionFahrenheit: String? = null,
    @DrawableRes val drawableIdFahrenheit: Int? = null
)
