package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.tomerpacific.laundry.BANGERS_FONT
import com.tomerpacific.laundry.BuildConfig
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.Utilities
import com.tomerpacific.laundry.model.LaundryCategory
import com.tomerpacific.laundry.viewmodel.MainViewModel

class LaundryCategoriesFragment: Fragment() {

    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model.getLaundryCategories().observe(viewLifecycleOwner, Observer<List<LaundryCategory>> { laundryCategories ->
            setupLaundryCategories(laundryCategories)
            setFontAndVersion()
            setClickListenersForButtons()
        })

        return inflater.inflate(R.layout.fragment_laundry_categories, container, false)
    }

    private fun setupLaundryCategories(laundryCategories: List<LaundryCategory>) {
        laundryCategories.forEach { laundryCategory ->
            val imageViewId = Utilities.getLaundryCategoryDrawableId(laundryCategory.name)
            requireActivity().findViewById<ImageView>(imageViewId).apply {
                setImageResource(laundryCategory.drawableId)
            }

            val textViewId: Int = Utilities.getLaundryCategoryTextviewId(laundryCategory.name)
            requireActivity().findViewById<TextView>(textViewId).apply {
                text = laundryCategory.name
            }
        }
    }

    private fun setFontAndVersion() {
        Utilities.setFont(
            requireActivity(),
            BANGERS_FONT,
            R.id.textView
        )

        requireActivity().findViewById<TextView>(R.id.app_version).apply {
            this?.text = getString(
                R.string.app_version,
                BuildConfig.VERSION_NAME
            )
        }
    }

    private fun setClickListenersForButtons() {

        requireActivity().findViewById<LinearLayout>(R.id.washing).apply {
            val fragment: LaundryCategoryFragment =
                LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.washing)!!)
            setOnClickListener {
                model.handleClickOnLaundryCategory(requireActivity(), fragment)
            }
        }

        requireActivity().findViewById<LinearLayout>(R.id.bleaching).apply {
            val fragment: LaundryCategoryFragment =
                LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.bleaching)!!)
            setOnClickListener {
                model.handleClickOnLaundryCategory(requireActivity(), fragment)
            }
        }

        requireActivity().findViewById<LinearLayout>(R.id.drying).apply {
            val fragment: LaundryCategoryFragment =
                LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.drying)!!)
            setOnClickListener {
                model.handleClickOnLaundryCategory(requireActivity(), fragment)
            }
        }

        requireActivity().findViewById<LinearLayout>(R.id.ironing).apply {
            val fragment: LaundryCategoryFragment =
                LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.ironing)!!)
            setOnClickListener {
                model.handleClickOnLaundryCategory(requireActivity(), fragment)
            }
        }
    }

}