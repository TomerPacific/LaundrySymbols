package com.tomerpacific.laundry.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.fragment.LaundryCategoryFragment
import com.tomerpacific.laundry.model.LaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol

class MainViewModel: ViewModel() {
    private val laundryCategories = MutableLiveData<List<LaundryCategory>>()

    init {
        loadLaundryCategories()
    }

    private fun loadLaundryCategories() {
        val washingLaundryCategory = LaundryCategory("Washing", R.drawable.washable)
        val bleachingLaundryCategory = LaundryCategory("Bleaching", R.drawable.bleach_allow)
        val dryingLaundryCategory = LaundryCategory("Drying", R.drawable.dry_cleaning_allow)
        val ironingLaundryCategory = LaundryCategory("Ironing", R.drawable.iron_allowed)
        val categoryList : List<LaundryCategory> = listOf(
            washingLaundryCategory, bleachingLaundryCategory, dryingLaundryCategory, ironingLaundryCategory
        )

        laundryCategories.postValue(categoryList)
    }

    fun getLaundryCategories() : LiveData<List<LaundryCategory>> {
        return laundryCategories
    }

    fun handleClickOnLaundryCategory(activity: FragmentActivity, fragment: LaundryCategoryFragment) {
        activity.supportFragmentManager.beginTransaction().
        replace(R.id.fragment_container_view, fragment).
        addToBackStack(null).commit()
    }

    fun getAmountOfColumnsForGridViewPerLaundryCategory(laundryCategory: String): Int {
        return when(laundryCategory) {
            LAUNDRY_CATEGORY_BLEACHING -> 3
            else -> 4
        }
    }

    fun getItemsForLaundryCategory(laundryCategory: String) : List<LaundrySymbol> {

        return when (laundryCategory) {
            LAUNDRY_CATEGORY_WASHING -> createWashingSymbols()
            LAUNDRY_CATEGORY_BLEACHING -> createBleachingSymbols()
            LAUNDRY_CATEGORY_DRYING -> createDryingSymbols()
            LAUNDRY_CATEGORY_IRONING -> createIroningSymbols()
            else -> listOf()
        }
    }

    private fun createWashingSymbols() : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol("Do Not Wash", "Do Not Wash", R.drawable.wash_do_not),
            LaundrySymbol("Regular Washing Allowed", "Regular Washing Allowed", R.drawable.washable),
            LaundrySymbol("Washing Cold", "Washing Cold", R.drawable.wash_30_degrees),
            LaundrySymbol("Washing Warm", "Washing Warm", R.drawable.wash_40_degrees_warm),
            LaundrySymbol("Washing Hot", "Washing Hot", R.drawable.wash_60_degrees_hot),
            LaundrySymbol("Synthetic Cycle", "Synthetic Cycle", R.drawable.wash_30_degrees_delicate),
            LaundrySymbol("Hand Wash", "Hand Wash", R.drawable.wash_by_hand)
        )
    }

    private fun createBleachingSymbols() : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol("Do Not Bleach", "Do Not Bleach", R.drawable.bleach_do_not),
            LaundrySymbol("Bleaching Allowed", "Bleaching Allowed", R.drawable.bleach_allow),
            LaundrySymbol("Bleach Non Chlorine", "Bleach Non Chlorine", R.drawable.bleach_non_chlorine)
        )
    }

    private fun createDryingSymbols(): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol("Do Not Dry Clean", "Do Not Dry Clean", R.drawable.dry_cleaning_do_not),
            LaundrySymbol("Dry Cleaning Allowed", "Dry Cleaning Allowed", R.drawable.dry_cleaning_allow),
            LaundrySymbol("Dry Cleaning Low Heat", "Dry Cleaning Low Heat", R.drawable.dry_cleaning_low_heat),
            LaundrySymbol("Dry Cleaning No Steam", "Dry Cleaning No Steam", R.drawable.dry_cleaning_no_steam),
            LaundrySymbol("Dry Cleaning Any Solvent", "Dry Cleaning Any Solvent", R.drawable.dry_cleaning_a),
            LaundrySymbol("Dry Cleaning Only", "Dry Cleaning Only", R.drawable.dry_cleaning_p),
            LaundrySymbol("Dry Cleaning Petroleum Solvent Only", "Dry Cleaning Petroleum Solvent Only", R.drawable.dry_cleaning_f),
            LaundrySymbol("Tumble Dry Low Heat", "Tumble Dry Low Heat", R.drawable.tumble_dry_low),
            LaundrySymbol("Tumble Dry High Heat", "Tumble Dry High Heat", R.drawable.tumble_dry_high),
            LaundrySymbol("Dry Flat", "Dry Flat", R.drawable.natural_drying_one_line),
            LaundrySymbol("Hang To Dry", "Hang To Dry", R.drawable.natural_drying_hang_to_dry),
            LaundrySymbol("Do Not Tumble Dry", "Do Not Tumble Dry", R.drawable.dryer_do_not_tumble_dry)
            )
    }

    private fun createIroningSymbols(): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol("Do Not Iron", "Do Not Iron", R.drawable.iron_do_not),
            LaundrySymbol("Ironing Allowed", "Ironing Allowed", R.drawable.iron_allowed),
            LaundrySymbol("Iron Low (Max 110\u00B0 Celsius)", "Iron Low (Max 110\u00B0 Celsius)", R.drawable.iron_low_setting),
            LaundrySymbol("Iron Medium (Max 150\u00B0 Celsius)", "Iron Medium (Max 150\u00B0 Celsius)", R.drawable.iron_medium_setting),
            LaundrySymbol( "Iron High (Max 200\u00B0 Celsius)",  "Iron High (Max 200\u00B0 Celsius)", R.drawable.iron_high_setting),
            LaundrySymbol( "Steaming Not Allowed",  "Steaming Not Allowed", R.drawable.iron_steam_not_allowed)
            )
    }

}