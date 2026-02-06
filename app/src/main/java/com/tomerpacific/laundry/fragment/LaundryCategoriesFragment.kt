package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalUriHandler
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.ui.screens.LaundryCategoriesScreen
import com.tomerpacific.laundry.viewmodel.MainViewModel

class LaundryCategoriesFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val uriHandler = LocalUriHandler.current
                LaundryCategoriesScreen(
                    onCategoryClick = {},
                    onLearnMoreClick = {},
                    onVersionClick = {
                        viewModel.handleClickOnVersion(uriHandler)
                    }
                )
            }
        }
    }

    private fun openLaundryCategory(laundryCategory:String) {

        val category = when (laundryCategory) {
            "washing" -> resources.getString(R.string.washing)
            "bleaching" -> resources.getString(R.string.bleaching)
            "drying" -> resources.getString(R.string.drying)
            "ironing" -> resources.getString(R.string.ironing)
            else -> ""
        }

        val fragment: LaundryCategoryFragment =
            LaundryCategoryFragment.newInstance(category)

    }

}