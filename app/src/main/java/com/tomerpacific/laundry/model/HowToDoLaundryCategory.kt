package com.tomerpacific.laundry.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HowToDoLaundryCategory(
    @StringRes val labelResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val contentDescriptionResId: Int,
)
