package com.tomerpacific.laundry.model

enum class HowToDoLaundryCategories {
    SEPARATING_LAUNDRY,
    TREATING_STAINS,
    LOAD_DETERGENT;
    companion object {
        fun convertHowToDoLaundryCategory(category: HowToDoLaundryCategories): String {
            return when(category) {
                HowToDoLaundryCategories.SEPARATING_LAUNDRY -> "Separating Laundry"
                HowToDoLaundryCategories.TREATING_STAINS -> "Treating Stains"
                HowToDoLaundryCategories.LOAD_DETERGENT -> "Load Detergent"
            }
        }
    }
}

data class HowToDoLaundryCategory(
    val name: HowToDoLaundryCategories,
    val descriptionId: Int,
    val drawableId: Int,
    val contentDescription: String,
)
