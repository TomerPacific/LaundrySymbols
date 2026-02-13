package com.tomerpacific.laundry.model

data class LaundrySymbol(
    val name: String,
    val description: String,
    val drawableId: Int,
    val temperature: Int? = null,
    val nameFahrenheit: String? = null,
    val drawableIdFahrenheit: Int? = null
)
