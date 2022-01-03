package com.tomerpacific.laundry

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomerpacific.laundry.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaundryCategoryInstrumentedTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun go_to_hand_washing_symbol() {
        onView(withId(R.id.washing_imageview)).perform(ViewActions.click())
        onView(withId(R.id.laundry_category_textview)).check(
            matches(withText(R.string.washing))
        )
        onView(withContentDescription("Hand Wash")).perform(ViewActions.click())
        onView(withId(R.id.symbol_header)).check(matches(withText("Hand Wash")))
    }

    @Test
    fun go_to_bleaching_allowed() {
        onView(withId(R.id.bleaching_imageview)).perform(ViewActions.click())
        onView(withId(R.id.laundry_category_textview)).check(
            matches(withText(R.string.bleaching))
        )
        onView(withContentDescription("Bleaching Allowed")).perform(ViewActions.click())
        onView(withId(R.id.symbol_header)).check(matches(withText("Bleaching Allowed")))
    }

    @Test
    fun go_to_hang_to_dry() {
        onView(withId(R.id.drying_imageview)).perform(ViewActions.click())
        onView(withId(R.id.laundry_category_textview)).check(
            matches(withText(R.string.drying))
        )
        onView(withContentDescription("Hang To Dry")).perform(ViewActions.click())
        onView(withId(R.id.symbol_header)).check(matches(withText("Hang To Dry")))
    }

    @Test
    fun go_to_iron_medium() {
        onView(withId(R.id.ironing_imageview)).perform(ViewActions.click())
        onView(withId(R.id.laundry_category_textview)).check(
            matches(withText(R.string.ironing))
        )
        onView(withContentDescription("Iron Medium (Max 150° Celsius)")).perform(ViewActions.click())
        onView(withId(R.id.symbol_header)).check(matches(withText("Iron Medium (Max 150° Celsius)")))
    }

}