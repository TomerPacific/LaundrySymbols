package com.tomerpacific.laundry

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomerpacific.laundry.activity.MainActivity
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testBackNavigation_popsFragment() {
        composeTestRule.onNodeWithTag("washing category").performClick()
        composeTestRule.onNodeWithTag("Do Not Wash").assertIsDisplayed()

        Espresso.pressBack()

        composeTestRule.onNodeWithTag("washing category").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Do Not Wash").assertDoesNotExist()
    }

    @Test
    fun testBackNavigation_finishesActivity() {
        try {
            Espresso.pressBack()
            fail("Expected NoActivityResumedException to be thrown.")
        } catch (e: NoActivityResumedException) {
            // Expected exception: test passes.
        }
    }

    @Test
    fun testHomeNavigationFromHowToDoLaundryFragment() {
        val howToDoLaundryButtonText = composeTestRule.activity.getString(R.string.how_to_do_laundry_button_text)
        composeTestRule.onNodeWithText(howToDoLaundryButtonText).performClick()

        val howToDoLaundryTitle = composeTestRule.activity.getString(R.string.how_to_do_laundry_title)
        composeTestRule.onNodeWithText(howToDoLaundryTitle).assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Home").performClick()

        val mainScreenTitle = composeTestRule.activity.getString(R.string.main_screen_title)
        composeTestRule.onNodeWithText(mainScreenTitle).assertIsDisplayed()
    }
}
