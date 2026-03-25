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
        return when (unit) {
            TemperatureUnit.CELSIUS -> description
            TemperatureUnit.FAHRENHEIT -> descriptionFahrenheit ?: description
        }
    }

    @DrawableRes
    fun drawableIdFor(unit: TemperatureUnit): Int {
        return when (unit) {
            TemperatureUnit.CELSIUS -> drawableId
            TemperatureUnit.FAHRENHEIT -> drawableIdFahrenheit ?: drawableId
        }
    }
}
