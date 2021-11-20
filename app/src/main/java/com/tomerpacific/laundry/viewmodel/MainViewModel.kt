package com.tomerpacific.laundry.viewmodel

import android.widget.GridView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.fragment.LaundryCategoryFragment
import com.tomerpacific.laundry.model.LaundryCategory

class MainViewModel: ViewModel() {
    private val laundryCategories = MutableLiveData<List<LaundryCategory>>()

    init {
        loadLaundryCategories()
    }

    private fun loadLaundryCategories() {
        val washingLaundryCategory : LaundryCategory = LaundryCategory("Washing", R.drawable.washable)
        val bleachingLaundryCategory : LaundryCategory = LaundryCategory("Bleaching", R.drawable.bleach_allow)
        val dryingLaundryCategory : LaundryCategory = LaundryCategory("Drying", R.drawable.dry_cleaning_allow)
        val ironingLaundryCategory : LaundryCategory = LaundryCategory("Ironing", R.drawable.iron_allowed)
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

    fun getItemsForLaundryCategory(laundryCategory: String) : List<String> {

        return when (laundryCategory) {
            LAUNDRY_CATEGORY_WASHING -> listOf(
                "wash_do_not",
                "washable",
                "wash_30_degrees",
                "wash_40_degrees_warm",
                "wash_60_degrees_hot",
                "wash_30_degrees_delicate",
                "wash_30_double_line",
                "wash_by_hand"
            )
            LAUNDRY_CATEGORY_BLEACHING -> listOf(
                "bleach_do_not",
                "bleach_allow",
                "bleach_non_chlorine"
            )
            LAUNDRY_CATEGORY_DRYING -> listOf(
                "dry_cleaning_do_not",
                "dry_cleaning_allow",
                "dry_cleaning_low_heat",
                "dry_cleaning_no_steam",
                "dry_cleaning_a",
                "dry_cleaning_p",
                "dry_cleaning_f",
                "tumble_dry_low",
                "tumble_dry_high",
                "natural_drying_hang_to_dry",
                "natural_drying_one_line",
                "dryer_do_not_tumble_dry"
            )
            LAUNDRY_CATEGORY_IRONING -> listOf(
                "iron_do_not",
                "iron_allowed",
                "iron_low_setting",
                "iron_medium_setting",
                "iron_high_setting",
                "iron_steam_not_allowed"
            )
            else -> listOf()
        }
    }

}