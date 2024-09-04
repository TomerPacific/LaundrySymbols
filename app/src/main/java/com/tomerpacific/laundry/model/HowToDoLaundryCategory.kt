package com.tomerpacific.laundry.model

enum class HowToDoLaundryCategories {
    SEPARATING_LAUNDRY,
    TREATING_STAINS,
    LOAD_DETERGENT,
    USING_BLEACH,
    ADDING_FABRIC_SOFTENER,
    WASH_CYCLE,
    WASH_TEMPERATURE;
    companion object {
        fun convertToString(category: HowToDoLaundryCategories): String {
            return category.name.split("_").joinToString(" ") { word ->
                word.lowercase().replaceFirstChar(
                    Char::titlecaseChar
                )
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
