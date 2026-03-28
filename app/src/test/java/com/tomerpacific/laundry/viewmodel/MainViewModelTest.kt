package com.tomerpacific.laundry.viewmodel

import android.app.Application
import androidx.compose.ui.platform.UriHandler
import com.tomerpacific.laundry.FakeLaundrySymbolsRepository
import com.tomerpacific.laundry.model.TemperatureUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var fakeRepository: FakeLaundrySymbolsRepository
    private lateinit var mockApplication: Application
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeLaundrySymbolsRepository()
        mockApplication = mock(Application::class.java)
        viewModel = MainViewModel(mockApplication, fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun findSymbolById_withValidId_returnsSymbol() {
        val symbol = viewModel.findSymbolById("washing-cold")
        assertNotNull(symbol)
        assertEquals("washing-cold", symbol?.id?.value)
    }

    @Test
    fun findSymbolById_withInvalidId_returnsNull() {
        val symbol = viewModel.findSymbolById("invalid-id")
        assertNull(symbol)
    }

    @Test
    fun findSymbolById_withEmptyString_returnsNull() {
        val symbol = viewModel.findSymbolById("")
        assertNull(symbol)
    }

    @Test
    fun onTemperatureUnitChanged_togglesCorrectly() {
        assertEquals(TemperatureUnit.CELSIUS, viewModel.temperatureUnit.value)
        
        viewModel.onTemperatureUnitChanged(true)
        assertEquals(TemperatureUnit.FAHRENHEIT, viewModel.temperatureUnit.value)
        
        viewModel.onTemperatureUnitChanged(false)
        assertEquals(TemperatureUnit.CELSIUS, viewModel.temperatureUnit.value)
    }

    @Test
    fun getItemsForLaundryCategory_withValidId_returnsList() {
        val items = viewModel.getItemsForLaundryCategory(101)
        assertEquals(1, items.size)
        assertEquals("washing-cold", items[0].id.value)
    }

    @Test
    fun getItemsForLaundryCategory_withInvalidId_returnsEmptyList() {
        val items = viewModel.getItemsForLaundryCategory(999)
        assertTrue(items.isEmpty())
    }

    @Test
    fun getHowToDoLaundryCategories_isNonEmptyAndOrdered() {
        val categories = viewModel.howToDoLaundryCategories
        assertTrue(categories.isNotEmpty())
        assertEquals(1, categories[0].labelResId)
        assertEquals(2, categories[1].labelResId)
    }

    @Test
    fun handleClickOnVersion_whenUriHandlerThrows_emitsErrorEvent() = runTest {
        val throwingUriHandler = object : UriHandler {
            override fun openUri(uri: String) {
                throw IllegalArgumentException("No activity found")
            }
        }

        viewModel.handleClickOnVersion(throwingUriHandler)

        val event = viewModel.uiEvents.first()
        assertTrue(event is MainUiEvent.ShowNoBrowserError)
    }
}
