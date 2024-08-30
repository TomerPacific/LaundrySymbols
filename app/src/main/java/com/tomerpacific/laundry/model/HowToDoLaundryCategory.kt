package com.tomerpacific.laundry.model

enum class HowToDoLaundryDrawerItems {
    SEPARATING_LAUNDRY,
    TREATING_STAINS,
}
data class HowToDoLaundryCategory(
    val name: HowToDoLaundryDrawerItems,
    val description: String,
    val drawable: Int
)