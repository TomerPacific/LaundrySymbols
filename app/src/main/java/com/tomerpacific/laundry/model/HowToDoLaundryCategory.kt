package com.tomerpacific.laundry.model

enum class HowToDoLaundryCategories {
    SEPARATING_LAUNDRY,
    TREATING_STAINS,
}
data class HowToDoLaundryCategory(
    val name: HowToDoLaundryCategories,
    val descriptionId: Int,
    val drawableId: Int,
    val contentDescription: String,
)