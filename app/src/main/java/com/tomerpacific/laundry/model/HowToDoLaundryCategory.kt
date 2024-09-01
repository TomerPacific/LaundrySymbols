package com.tomerpacific.laundry.model

enum class HowToDoLaundryCategories {
    SEPARATING_LAUNDRY,
    TREATING_STAINS,
    LOAD_DETERGENT,
    USING_BLEACH,
    ADDING_FABRIC_SOFTENER;
    companion object {
        fun convertHowToDoLaundryCategory(category: HowToDoLaundryCategories): String {
            return when(category) {
                SEPARATING_LAUNDRY -> "Separating Laundry"
                TREATING_STAINS -> "Treating Stains"
                LOAD_DETERGENT -> "Load Detergent"
                USING_BLEACH -> "Using Bleach"
                ADDING_FABRIC_SOFTENER -> "Adding Fabric Softener"

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
