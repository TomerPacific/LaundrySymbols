package com.tomerpacific.laundry.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LaundryCategory(
    @StringRes val labelResId: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val contentDescriptionResId: Int,
    val testTag: String
)
