package com.tomerpacific.laundry.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.fragment.LaundryCategoryFragment
import com.tomerpacific.laundry.fragment.LaundrySymbolFragment
import com.tomerpacific.laundry.model.LaundrySymbol

class MainViewModel: ViewModel() {

    private val laundrySymbolsRepository = LaundrySymbolsRepository()

    fun handleClickOnLaundryCategory(activity: FragmentActivity, fragment: LaundryCategoryFragment) {
        activity.supportFragmentManager.beginTransaction().
        replace(R.id.fragment_container_view, fragment).
        addToBackStack(null).commit()
    }

    fun handleClickOnLaundrySymbol(activity: FragmentActivity, fragment: LaundrySymbolFragment) {
        activity.supportFragmentManager.beginTransaction().
        replace(R.id.fragment_container_view, fragment).
        addToBackStack(null).commit()
    }

    fun getItemsForLaundryCategory(laundryCategory: String) : List<LaundrySymbol> {

        return when (laundryCategory) {
            LAUNDRY_CATEGORY_WASHING -> laundrySymbolsRepository.createWashingSymbols()
            LAUNDRY_CATEGORY_BLEACHING -> laundrySymbolsRepository.createBleachingSymbols()
            LAUNDRY_CATEGORY_DRYING -> laundrySymbolsRepository.createDryingSymbols()
            LAUNDRY_CATEGORY_IRONING -> laundrySymbolsRepository.createIroningSymbols()
            else -> listOf()
        }
    }

}