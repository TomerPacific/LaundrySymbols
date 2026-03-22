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

    private val _temperatureUnit = mutableStateOf(TemperatureUnit.CELSIUS)
    val temperatureUnit: State<TemperatureUnit> = _temperatureUnit

    private val _howToDoLaundryCategories by lazy {
        LaundrySymbolsRepository.createHowToDoLaundryCategories()
    }

    private val _laundryCategoryItems by lazy {
        LaundrySymbolsRepository.createLaundryCategoryItems(getApplication())
    }

    private val _selectedDrawerItem = mutableStateOf(_howToDoLaundryCategories[0])
    val selectedDrawerItem: State<HowToDoLaundryCategory> = _selectedDrawerItem

    private val websiteUrls = listOf(
        "https://tomerpacific.github.io/Portfolio/",
        "https://github.com/TomerPacific",
        "https://medium.com/@tomerpacific",
        "https://play.google.com/store/apps/developer?id=tomerpacific"
    )

    fun getItemsForLaundryCategory(laundryCategory: Int) : List<LaundrySymbol> {
        return _laundryCategoryItems.getOrElse(laundryCategory) { emptyList() }
    }

    fun getHowToDoLaundryCategories(): List<HowToDoLaundryCategory> {
        return _howToDoLaundryCategories
    }

    fun findSymbolById(id: String): LaundrySymbol? {
        return _laundryCategoryItems.values.flatten().find { it.id == id }
    }

    fun handleClickOnHowToDoLaundryCategories(howToDoLaundryCategory: HowToDoLaundryCategory) {
        _selectedDrawerItem.value = howToDoLaundryCategory
    }

    fun onTemperatureUnitChanged(isFahrenheit: Boolean) {
        _temperatureUnit.value = if (isFahrenheit) {
            TemperatureUnit.FAHRENHEIT
        } else {
            TemperatureUnit.CELSIUS
        }
    }

    fun handleClickOnVersion(uriHandler: UriHandler) {
        uriHandler.openUri(websiteUrls.random())
    }
}
