package com.tomerpacific.laundry.model

import androidx.annotation.DrawableRes

@JvmInline
value class SymbolId(val value: String)

data class LaundrySymbol(
    val id: SymbolId,
    val name: String,
    val description: String,
    @DrawableRes val drawableId: Int,
    val temperature: Int? = null,
    val descriptionFahrenheit: String? = null,
    @DrawableRes val drawableIdFahrenheit: Int? = null
)
