package com.tomerpacific.laundry.viewmodel


import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.AndroidViewModel
import com.tomerpacific.laundry.LaundrySymbolsRepository
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol
import com.tomerpacific.laundry.model.TemperatureUnit

class MainViewModel(application: Application): AndroidViewModel(application ) {

    private val laundrySymbolsRepository = LaundrySymbolsRepository()

    private val _temperatureUnit = mutableStateOf(TemperatureUnit.CELSIUS)
    val temperatureUnit: State<TemperatureUnit> = _temperatureUnit

    private val howToDoLaundryCategories = laundrySymbolsRepository.createHowToDoLaundryCategories()

    private val laundryCategoryItems = laundrySymbolsRepository.createLaundryCategoryItems(application)

    private val _selectedDrawerItem = mutableStateOf(howToDoLaundryCategories[0])
    val selectedDrawerItem: State<HowToDoLaundryCategory> = _selectedDrawerItem

    private val websiteUrls = listOf(
        "https://tomerpacific.github.io/Portfolio/",
        "https://github.com/TomerPacific",
        "https://medium.com/@tomerpacific",
        "https://play.google.com/store/apps/developer?id=tomerpacific"
    )

    fun getItemsForLaundryCategory(laundryCategory: Int) : List<LaundrySymbol> {
        return laundryCategoryItems.getOrElse(laundryCategory) { emptyList() }
    }

    fun getHowToDoLaundryCategories(): List<HowToDoLaundryCategory> {
        return howToDoLaundryCategories
    }

    fun findSymbolByName(name: String): LaundrySymbol? {
        return laundryCategoryItems.values.flatten().find { it.name == name }
    }

    fun handleClickOnHowToDoLaundryCategories(howToDoLaundryCategory: HowToDoLaundryCategory) {
        _selectedDrawerItem.value = howToDoLaundryCategory
    }

    fun toggleTemperatureUnit() {
        _temperatureUnit.value = when (_temperatureUnit.value) {
            TemperatureUnit.CELSIUS -> TemperatureUnit.FAHRENHEIT
            TemperatureUnit.FAHRENHEIT -> TemperatureUnit.CELSIUS
        }
    }

    fun handleClickOnVersion(uriHandler: UriHandler) {
        uriHandler.openUri(websiteUrls.random())
    }
}