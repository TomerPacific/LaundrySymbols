package com.tomerpacific.laundry.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LaundryCategory(
    @StringRes val name: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val contentDescriptionId: Int,
    val testTag: String
)
