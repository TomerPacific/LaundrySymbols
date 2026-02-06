package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.LAUNDRY_CATEGORY_KEY
import com.tomerpacific.laundry.model.LaundrySymbol
import com.tomerpacific.laundry.ui.screens.LaundryCategoryScreen
import com.tomerpacific.laundry.viewmodel.MainViewModel


class LaundryCategoryFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    companion object {
        fun newInstance(laundryCategory: String): LaundryCategoryFragment {
            val fragment = LaundryCategoryFragment()
            val args = Bundle()
            args.putString(LAUNDRY_CATEGORY_KEY, laundryCategory)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val laundryCategoryName: String = arguments?.getString(LAUNDRY_CATEGORY_KEY) ?: ""

        return ComposeView(requireContext()).apply {
            setContent {
                LaundryCategoryScreen(
                    laundryCategory = laundryCategoryName,
                    laundrySymbols = viewModel.getItemsForLaundryCategory(
                        laundryCategoryName,
                        requireContext()
                    ),
                    onSymbolClick = {}
                )
            }
        }
    }

    private fun navigateToLaundrySymbolFragment(laundrySymbol: LaundrySymbol) {
        val fragment: LaundrySymbolFragment =
            LaundrySymbolFragment.newInstance(laundrySymbol)
    }
}