package com.tomerpacific.laundry

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomerpacific.laundry.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaundryCategoriesTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun goToWashingCategory() {
        val washingCategoryText = composeTestRule.activity.getString(R.string.washing)
        composeTestRule.onNodeWithText(washingCategoryText).assertIsDisplayed()
        composeTestRule.onNodeWithTag("washing category").performClick()
        composeTestRule.onNodeWithTag("do-not-wash").assertIsDisplayed()
    }

    @Test
    fun goToBleachingCategory() {
        val bleachingCategoryText = composeTestRule.activity.getString(R.string.bleaching)
        composeTestRule.onNodeWithText(bleachingCategoryText).assertIsDisplayed()
        composeTestRule.onNodeWithTag("bleaching category").performClick()
        composeTestRule.onNodeWithTag("bleaching-allowed").assertIsDisplayed()
    }

    @Test
    fun goToAllowIroningSymbol() {
        val ironingCategoryText = composeTestRule.activity.getString(R.string.ironing)
        composeTestRule.onNodeWithText(ironingCategoryText).assertIsDisplayed()
        composeTestRule.onNodeWithTag("ironing category").performClick()
        composeTestRule.onNodeWithTag("ironing-allowed").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ironing-allowed").performClick()
        composeTestRule.onNodeWithTag("do-not-iron").assertDoesNotExist()
    }

    @Test
    fun testBackNavigationFromCategoryScreen() {
        val washingCategoryText = composeTestRule.activity.getString(R.string.washing)
        composeTestRule.onNodeWithTag("washing category").performClick()
        composeTestRule.onNodeWithTag("do-not-wash").assertIsDisplayed()

        val backButtonContentDescription = composeTestRule.activity.getString(R.string.back_button_content_description)
        composeTestRule.onNodeWithContentDescription(backButtonContentDescription).performClick()

        composeTestRule.onNodeWithText(washingCategoryText).assertIsDisplayed()
        composeTestRule.onNodeWithTag("do-not-wash").assertDoesNotExist()
    }

    @Test
    fun testBackNavigationFromSymbolScreen() {
        composeTestRule.onNodeWithTag("washing category").performClick()
        composeTestRule.onNodeWithTag("do-not-wash").performClick()
        
        val backButtonContentDescription = composeTestRule.activity.getString(R.string.back_button_content_description)
        composeTestRule.onNodeWithContentDescription(backButtonContentDescription).performClick()

        composeTestRule.onNodeWithTag("do-not-wash").assertIsDisplayed()
        composeTestRule.onNodeWithTag("symbol_description_text").assertDoesNotExist()
    }

}