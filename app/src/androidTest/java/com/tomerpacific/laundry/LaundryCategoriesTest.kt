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
        composeTestRule.onNodeWithTag("Do Not Wash").assertIsDisplayed()
    }

    @Test
    fun goToBleachingCategory() {
        val bleachingCategoryText = composeTestRule.activity.getString(R.string.bleaching)
        composeTestRule.onNodeWithText(bleachingCategoryText).assertIsDisplayed()
        composeTestRule.onNodeWithTag("bleaching category").performClick()
        composeTestRule.onNodeWithTag("Bleaching Allowed").assertIsDisplayed()
    }

    @Test
    fun goToAllowIroningSymbol() {
        val ironingCategoryText = composeTestRule.activity.getString(R.string.ironing)
        composeTestRule.onNodeWithText(ironingCategoryText).assertIsDisplayed()
        composeTestRule.onNodeWithTag("ironing category").performClick()
        composeTestRule.onNodeWithTag("Ironing Allowed").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Ironing Allowed").performClick()
        composeTestRule.onNodeWithTag("Do Not Iron").assertDoesNotExist()
    }

}