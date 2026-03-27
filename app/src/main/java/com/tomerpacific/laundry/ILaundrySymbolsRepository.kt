package com.tomerpacific.laundry

import android.content.Context
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.model.LaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol

interface ILaundrySymbolsRepository {
    fun createWashingSymbols(context: Context): List<LaundrySymbol>
    fun createBleachingSymbols(context: Context): List<LaundrySymbol>
    fun createDryingSymbols(context: Context): List<LaundrySymbol>
    fun createIroningSymbols(context: Context): List<LaundrySymbol>
    fun createHowToDoLaundryCategories(): List<HowToDoLaundryCategory>
    fun createLaundryCategories(): List<LaundryCategory>
    fun createLaundryCategoryItems(context: Context): Map<Int, List<LaundrySymbol>>
}
