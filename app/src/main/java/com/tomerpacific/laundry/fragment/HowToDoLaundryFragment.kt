package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.ui.screens.HowToDoLaundryScreen
import com.tomerpacific.laundry.viewmodel.MainViewModel

class HowToDoLaundryFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                HowToDoLaundryScreen(
                    categories = viewModel.getHowToDoLaundryCategories(),
                    selectedCategory = viewModel.selectedDrawerItem.value,
                    onCategoryClick = {
                        viewModel.handleClickOnHowToDoLaundryCategories(it)
                    },
                    onHomeClick = {
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                )
            }
        }
    }
}