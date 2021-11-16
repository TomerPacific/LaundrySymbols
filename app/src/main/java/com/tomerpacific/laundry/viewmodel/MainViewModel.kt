package com.tomerpacific.laundry.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.model.LaundryCategory

class MainViewModel: ViewModel() {
    private val laundryCategories : MutableLiveData<List<LaundryCategory>> by lazy {
        MutableLiveData<List<LaundryCategory>>().also {
            loadLaundryCategories()
        }
    }

    fun getLaundryCategories(): LiveData<List<LaundryCategory>> {
        return laundryCategories
    }

    private fun loadLaundryCategories() {
        val washingLaundryCategory : LaundryCategory = LaundryCategory("Washing", "drawable://" + R.drawable.washable)
        val bleachingLaundryCategory : LaundryCategory = LaundryCategory("Bleaching", "drawable://" + R.drawable.bleach_allow)
        val dryingLaundryCategory : LaundryCategory = LaundryCategory("Drying", "drawable://" + R.drawable.dry_cleaning_allow)
        val ironingLaundryCategory : LaundryCategory = LaundryCategory("Ironing", "drawable://" + R.drawable.iron_allowed)
        val categoryList : List<LaundryCategory> = listOf(
            washingLaundryCategory, bleachingLaundryCategory, dryingLaundryCategory, ironingLaundryCategory
        )
        laundryCategories.value = categoryList
    }




}