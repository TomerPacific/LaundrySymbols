
package com.tomerpacific.laundry

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomerpacific.laundry.activity.MainActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testBackNavigation_popsFragment() {
        // Navigate to a fragment
        composeTestRule.onNodeWithTag("washing category").performClick()
        composeTestRule.onNodeWithTag("Do Not Wash").assertIsDisplayed()

        // Press back
        Espresso.pressBack()

        // Assert we are back to the main screen
        composeTestRule.onNodeWithTag("washing category").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Do Not Wash").assertDoesNotExist()
    }

    @Test
    fun testBackNavigation_finishesActivity() {
        // Assert activity is not finished
        assertTrue(!composeTestRule.activity.isFinishing)

        // Press back
        Espresso.pressBack()

        // Assert activity is finishing
        assertTrue(composeTestRule.activity.isFinishing)
    }
}
