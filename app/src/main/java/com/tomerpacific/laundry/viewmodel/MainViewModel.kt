package com.tomerpacific.laundry.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var laundryCategories = MutableLiveData<ArrayList<String>>()

}