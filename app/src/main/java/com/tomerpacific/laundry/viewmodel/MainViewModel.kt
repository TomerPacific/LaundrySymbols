package com.tomerpacific.laundry.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.fragment.HowToDoLaundryFragment
import com.tomerpacific.laundry.fragment.LaundryCategoryFragment
import com.tomerpacific.laundry.fragment.LaundrySymbolFragment
import com.tomerpacific.laundry.model.LaundrySymbol


enum class HowToDoLaundryDrawerItems {
    SEPARATING_LAUNDRY,
}
class MainViewModel: ViewModel() {

    private val laundrySymbolsRepository = LaundrySymbolsRepository()

    private val _selectedDrawerItem = mutableStateOf(HowToDoLaundryDrawerItems.SEPARATING_LAUNDRY)
    val selectedDrawerItem = _selectedDrawerItem

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

    fun getItemsForLaundryCategory(laundryCategory: String, context: Context) : List<LaundrySymbol> {

        return when (laundryCategory) {
            LAUNDRY_CATEGORY_WASHING -> laundrySymbolsRepository.createWashingSymbols(context)
            LAUNDRY_CATEGORY_BLEACHING -> laundrySymbolsRepository.createBleachingSymbols(context)
            LAUNDRY_CATEGORY_DRYING -> laundrySymbolsRepository.createDryingSymbols(context)
            LAUNDRY_CATEGORY_IRONING -> laundrySymbolsRepository.createIroningSymbols(context)
            else -> listOf()
        }
    }

    fun handleClickOnLearnHowToDoLaundry(activity: FragmentActivity) {
        activity.supportFragmentManager.beginTransaction().
        replace(R.id.fragment_container_view, HowToDoLaundryFragment()).
        addToBackStack(null).commit()
    }

    fun handleClickOnHowToDoLaundryCategories(categoryTitle: String) {
        when (categoryTitle) {
            HowToDoLaundryDrawerItems.SEPARATING_LAUNDRY.name -> _selectedDrawerItem.value = HowToDoLaundryDrawerItems.SEPARATING_LAUNDRY
        }
    }

}