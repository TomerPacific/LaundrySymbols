package com.tomerpacific.laundry.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.LaundrySymbolsRepository
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol

class MainViewModel: ViewModel() {

    private val laundrySymbolsRepository = LaundrySymbolsRepository()

    private val howToDoLaundryCategories = laundrySymbolsRepository.createHowToDoLaundryCategories()

    private val _selectedDrawerItem = mutableStateOf(howToDoLaundryCategories[0])
    val selectedDrawerItem = _selectedDrawerItem

    private val websiteUrls = listOf(
        "https://tomerpacific.github.io/Portfolio/",
        "https://github.com/TomerPacific",
        "https://medium.com/@tomerpacific",
        "https://play.google.com/store/apps/developer?id=tomerpacific"
    )

    fun getItemsForLaundryCategory(laundryCategory: String, context: Context) : List<LaundrySymbol> {

        return when (laundryCategory) {
            context.resources.getString(R.string.washing) -> laundrySymbolsRepository.createWashingSymbols(context)
            context.resources.getString(R.string.bleaching) -> laundrySymbolsRepository.createBleachingSymbols(context)
            context.resources.getString(R.string.drying) -> laundrySymbolsRepository.createDryingSymbols(context)
            context.resources.getString(R.string.ironing) -> laundrySymbolsRepository.createIroningSymbols(context)
            else -> listOf()
        }
    }

    fun getHowToDoLaundryCategories(): List<HowToDoLaundryCategory> {
        return howToDoLaundryCategories
    }

    fun handleClickOnHowToDoLaundryCategories(howToDoLaundryCategory: HowToDoLaundryCategory) {
        _selectedDrawerItem.value = howToDoLaundryCategory
    }


    fun handleClickOnVersion(uriHandler: UriHandler) {
        uriHandler.openUri(websiteUrls.random())
    }
}