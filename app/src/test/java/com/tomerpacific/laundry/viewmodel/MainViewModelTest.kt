package com.tomerpacific.laundry.viewmodel

import android.app.Application
import android.content.ActivityNotFoundException
import androidx.compose.ui.platform.UriHandler
import com.tomerpacific.laundry.FakeLaundrySymbolsRepository
import com.tomerpacific.laundry.model.TemperatureUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.mock

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var fakeRepository: FakeLaundrySymbolsRepository
    private lateinit var mockApplication: Application

    @Before
    fun setUp() {
        fakeRepository = FakeLaundrySymbolsRepository()
        mockApplication = mock(Application::class.java)
        viewModel = MainViewModel(mockApplication, fakeRepository)
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
    fun handleClickOnVersion_withValidUri_returnsTrue() {
        val mockUriHandler = mock(UriHandler::class.java)
        doNothing().`when`(mockUriHandler).openUri(anyString())
        val result = viewModel.handleClickOnVersion(mockUriHandler)
        assertTrue(result)
    }

    @Test
    fun handleClickOnVersion_withActivityNotFoundException_returnsFalse() {
        val mockUriHandler = mock(UriHandler::class.java)
        doThrow(ActivityNotFoundException()).`when`(mockUriHandler).openUri(anyString())
        val result = viewModel.handleClickOnVersion(mockUriHandler)
        assertFalse(result)
    }

    @Test
    fun handleClickOnVersion_withIllegalArgumentException_returnsFalse() {
        val mockUriHandler = mock(UriHandler::class.java)
        doThrow(IllegalArgumentException()).`when`(mockUriHandler).openUri(anyString())
        val result = viewModel.handleClickOnVersion(mockUriHandler)
        assertFalse(result)
    }
}
