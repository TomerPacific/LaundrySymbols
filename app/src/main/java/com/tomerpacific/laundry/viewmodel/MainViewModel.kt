package com.tomerpacific.laundry.viewmodel


import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tomerpacific.laundry.ILaundrySymbolsRepository
import com.tomerpacific.laundry.LaundrySymbolsRepository
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol
import com.tomerpacific.laundry.model.TemperatureUnit
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel @JvmOverloads constructor(
    application: Application,
    private val laundrySymbolsRepository: ILaundrySymbolsRepository = LaundrySymbolsRepository()
): AndroidViewModel(application ) {

    private val _temperatureUnit = mutableStateOf(TemperatureUnit.CELSIUS)
    val temperatureUnit: State<TemperatureUnit> = _temperatureUnit

    val howToDoLaundryCategories by lazy {
        laundrySymbolsRepository.createHowToDoLaundryCategories()
    }

    val laundryCategories by lazy {
        laundrySymbolsRepository.createLaundryCategories()
    }

    private val laundryCategoryItems by lazy {
        laundrySymbolsRepository.createLaundryCategoryItems(getApplication())
    }

    private val symbolIndex: Map<String, LaundrySymbol> by lazy {
        laundryCategoryItems.values.flatten().associateBy { it.id.value }
    }

    private val _selectedDrawerItem: MutableState<HowToDoLaundryCategory> by lazy {
        mutableStateOf(howToDoLaundryCategories[0])
    }
    val selectedDrawerItem: State<HowToDoLaundryCategory> get() = _selectedDrawerItem

    private val _uiEvents = MutableSharedFlow<MainUiEvent>()
    val uiEvents: SharedFlow<MainUiEvent> = _uiEvents.asSharedFlow()

    private val websiteUrls = listOf(
        "https://tomerpacific.github.io/Portfolio/",
        "https://github.com/TomerPacific",
        "https://medium.com/@tomerpacific",
        "https://play.google.com/store/apps/developer?id=tomerpacific"
    )

    fun getItemsForLaundryCategory(laundryCategory: Int) : List<LaundrySymbol> {
        return laundryCategoryItems.getOrElse(laundryCategory) { emptyList() }
    }

    fun findSymbolById(id: String): LaundrySymbol? = symbolIndex[id]

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
        try {
            uriHandler.openUri(websiteUrls.random())
        } catch (e: IllegalArgumentException) {
            Log.e("MainViewModel", "Could not open URI: No activity found", e)
            sendUiEvent(MainUiEvent.ShowNoBrowserError)
        }
    }

    private fun sendUiEvent(event: MainUiEvent) {
        viewModelScope.launch {
            _uiEvents.emit(event)
        }
    }
}

sealed class MainUiEvent {
    object ShowNoBrowserError : MainUiEvent()
}
