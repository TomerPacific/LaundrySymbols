package com.tomerpacific.laundry

import android.content.Context
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.model.LaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol
import com.tomerpacific.laundry.model.SymbolId

class FakeLaundrySymbolsRepository : ILaundrySymbolsRepository {
    override fun createWashingSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                id = SymbolId("washing-cold"),
                name = "Wash Cold",
                description = "Wash at 30 degrees",
                drawableId = 1,
                temperature = 30
            )
        )
    }

    override fun createBleachingSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                id = SymbolId("bleaching-allowed"),
                name = "Bleaching Allowed",
                description = "You can bleach",
                drawableId = 2
            )
        )
    }

    override fun createDryingSymbols(context: Context): List<LaundrySymbol> {
        return emptyList()
    }

    override fun createIroningSymbols(context: Context): List<LaundrySymbol> {
        return emptyList()
    }

    override fun createHowToDoLaundryCategories(): List<HowToDoLaundryCategory> {
        return listOf(
            HowToDoLaundryCategory(1, 1, 1, 1),
            HowToDoLaundryCategory(2, 2, 2, 2)
        )
    }

    override fun createLaundryCategories(): List<LaundryCategory> {
        return listOf(
            LaundryCategory(101, 101, 101, "washing category"),
            LaundryCategory(102, 102, 102, "bleaching category")
        )
    }

    override fun createLaundryCategoryItems(context: Context): Map<Int, List<LaundrySymbol>> {
        return mapOf(
            101 to createWashingSymbols(context),
            102 to createBleachingSymbols(context)
        )
    }
}
