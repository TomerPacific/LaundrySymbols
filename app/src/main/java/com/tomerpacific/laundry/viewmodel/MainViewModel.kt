package com.tomerpacific.laundry.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.R
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

}