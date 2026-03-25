package com.tomerpacific.laundry.model

import androidx.annotation.DrawableRes

data class LaundrySymbol(
    val id: SymbolId,
    val name: String,
    val description: String,
    @DrawableRes val drawableId: Int,
    val temperature: Int? = null,
    val descriptionFahrenheit: String? = null,
    @DrawableRes val drawableIdFahrenheit: Int? = null
) {
    fun descriptionFor(unit: TemperatureUnit): String {
        return if (unit == TemperatureUnit.FAHRENHEIT) {
            descriptionFahrenheit ?: description
        } else {
            description
        }
    }

    @DrawableRes
    fun drawableIdFor(unit: TemperatureUnit): Int {
        return if (unit == TemperatureUnit.FAHRENHEIT) {
            drawableIdFahrenheit ?: drawableId
        } else {
            drawableId
        }
    }
}
