package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.viewmodel.MainViewModel

class LaundryCategoriesFragment2: Fragment() {
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val view: View = inflater.inflate(R.layout.fragment_laundry_categories, container, false)
        return view
    }

}