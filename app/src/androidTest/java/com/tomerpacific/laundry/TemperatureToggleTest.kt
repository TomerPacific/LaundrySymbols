package com.tomerpacific.laundry

import android.app.Application
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.tomerpacific.laundry.ui.screens.LaundryCategoryScreen
import com.tomerpacific.laundry.ui.screens.LaundrySymbolScreen
import com.tomerpacific.laundry.viewmodel.MainViewModel
import org.junit.Rule
import org.junit.Test

class TemperatureToggleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testToggleSwitchIsDisplayed_whenSymbolsWithTemperatureArePresent() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = MainViewModel(application)
        val laundryCategory = R.string.washing

        composeTestRule.setContent {
            LaundryCategoryScreen(laundryCategory = laundryCategory, viewModel = viewModel, onSymbolClick = {})
        }

        composeTestRule.onNodeWithTag("temperature_unit_toggle").assertIsDisplayed()
    }

    @Test
    fun testToggleSwitchIsNotDisplayed_whenSymbolsWithTemperatureAreNotPresent() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = MainViewModel(application)
        val laundryCategory = R.string.bleaching

        composeTestRule.setContent {
            LaundryCategoryScreen(laundryCategory = laundryCategory, viewModel = viewModel, onSymbolClick = {})
        }

        composeTestRule.onNodeWithTag("temperature_unit_toggle").assertDoesNotExist()
    }

    @Test
    fun testTogglingSwitchChangesTemperatureUnit() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = MainViewModel(application)
        val laundryCategory = R.string.washing
        val washingSymbol = viewModel.getItemsForLaundryCategory(laundryCategory).first { it.temperature != null }

        composeTestRule.setContent {
            LaundryCategoryScreen(laundryCategory = laundryCategory, viewModel = viewModel, onSymbolClick = {})
        }

        composeTestRule.onNodeWithTag("temperature_unit_toggle").performClick()

        composeTestRule.onNodeWithTag(washingSymbol.id)
            .assertContentDescriptionContains("86", substring = true)

    }

    @Test
    fun testTemperatureUnitPersistsOnNavigation() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = MainViewModel(application)
        val laundryCategory = R.string.washing
        val symbol = viewModel.getItemsForLaundryCategory(laundryCategory).first { it.temperature != null }

        composeTestRule.setContent {
            LaundrySymbolScreen(viewModel = viewModel, symbolId = symbol.id)
        }

        viewModel.onTemperatureUnitChanged(true)

        composeTestRule.onNodeWithTag("symbol_description_text").assertTextContains("86", substring = true)

    }

    @Test
    fun testSwitchHasAccessibilityLabel() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = MainViewModel(application)
        val laundryCategory = R.string.washing

        composeTestRule.setContent {
            LaundryCategoryScreen(laundryCategory = laundryCategory, viewModel = viewModel, onSymbolClick = {})
        }

        composeTestRule.onNodeWithTag("temperature_unit_toggle")
            .assertContentDescriptionContains("Temperature unit toggle, currently showing Celsius", substring = true)

        composeTestRule.onNodeWithTag("temperature_unit_toggle").performClick()

        composeTestRule.onNodeWithTag("temperature_unit_toggle")
            .assertContentDescriptionContains("Temperature unit toggle, currently showing Fahrenheit", substring = true)
    }

}