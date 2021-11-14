package com.tomerpacific.laundry.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomerpacific.laundry.model.LaundryCategory

class MainViewModel: ViewModel() {
    var laundryCategories = MutableLiveData<ArrayList<LaundryCategory>>()



}