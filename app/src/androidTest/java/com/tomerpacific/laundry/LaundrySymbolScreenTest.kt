package com.tomerpacific.laundry

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.tomerpacific.laundry.ui.screens.LaundrySymbolScreen
import com.tomerpacific.laundry.viewmodel.MainViewModel
import org.junit.Rule
import org.junit.Test

class LaundrySymbolScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validSymbolIdDisplaysNameAndDescription() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = MainViewModel(application)
        val symbolId = "washing-cold"
        val symbol = viewModel.findSymbolById(symbolId)!!

        composeTestRule.setContent {
            LaundrySymbolScreen(viewModel = viewModel, symbolId = symbolId)
        }

        composeTestRule.onNodeWithText(symbol.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("symbol_description_text").assertIsDisplayed()
    }

    @Test
    fun invalidSymbolIdDisplaysNotFoundState() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = MainViewModel(application)
        val symbolId = "invalid-id"
        val notFoundText = application.getString(R.string.symbol_not_found)

        composeTestRule.setContent {
            LaundrySymbolScreen(viewModel = viewModel, symbolId = symbolId)
        }

        composeTestRule.onNodeWithText(notFoundText).assertIsDisplayed()
    }
}
