package com.tomerpacific.laundry

import androidx.compose.ui.semantics.SemanticsPropertyKey
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

}