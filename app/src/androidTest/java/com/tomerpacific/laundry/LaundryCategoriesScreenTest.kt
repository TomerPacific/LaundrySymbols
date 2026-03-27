package com.tomerpacific.laundry

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.tomerpacific.laundry.model.LaundryCategory
import com.tomerpacific.laundry.ui.screens.LaundryCategoriesScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LaundryCategoriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun allFourCategoryTilesArePresentAndTappable() {
        val categories = listOf(
            LaundryCategory(R.string.washing, R.drawable.washable, R.string.washing_symbol, "washing category"),
            LaundryCategory(R.string.bleaching, R.drawable.bleach_allow, R.string.bleaching_symbol, "bleaching category"),
            LaundryCategory(R.string.drying, R.drawable.dry_cleaning_allow, R.string.drying_symbol, "drying category"),
            LaundryCategory(R.string.ironing, R.drawable.iron_allowed, R.string.ironing_symbol, "ironing category")
        )

        var clickedCategoryResId = -1

        composeTestRule.setContent {
            LaundryCategoriesScreen(
                categories = categories,
                onCategoryClick = { clickedCategoryResId = it },
                onLearnMoreClick = {},
                onVersionClick = {}
            )
        }

        val testTags = listOf("washing category", "bleaching category", "drying category", "ironing category")
        val labelResIds = listOf(R.string.washing, R.string.bleaching, R.string.drying, R.string.ironing)

        testTags.zip(labelResIds).forEach { (tag, resId) ->
            composeTestRule.onNodeWithTag(tag).assertIsDisplayed().performClick()
            composeTestRule.runOnIdle {
                assertEquals(resId, clickedCategoryResId)
            }
        }
    }
}
