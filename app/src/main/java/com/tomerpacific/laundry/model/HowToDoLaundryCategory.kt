package com.tomerpacific.laundry.model

enum class HowToDoLaundryCategories {
    SEPARATING_LAUNDRY,
    TREATING_STAINS,
}
data class HowToDoLaundryCategory(
    val name: HowToDoLaundryCategories,
    val description: String,
    val drawableId: Int,
    val contentDescription: String,
)