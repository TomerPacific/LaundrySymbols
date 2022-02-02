package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.viewmodel.MainViewModel

class LaundryCategoryFragment : Fragment() {

    private lateinit var gridViewAdapter: GridViewAdapter
    private val model: MainViewModel by activityViewModels()

    companion object {
        fun newInstance(laundryCategory: String) : LaundryCategoryFragment {
            val fragment = LaundryCategoryFragment()
            val args = Bundle()
            args.putString(LAUNDRY_CATEGORY_KEY, laundryCategory)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_laundry_category, container, false)
        val laundryCategoryTextView : TextView = view.findViewById(R.id.laundry_category_textview)
        val laundryCategory : String = this.arguments?.getString(LAUNDRY_CATEGORY_KEY) ?: return view

        laundryCategoryTextView.text = laundryCategory

        Utilities.setFont(
            view.findViewById(R.id.laundry_category_textview),
            BANGERS_FONT
        )

        val gridLayout: GridView = view.findViewById(R.id.grid_layout)
        setupGridView(gridLayout, laundryCategory)

        return view
    }

    private fun setupGridView(gridLayout : GridView, laundryCategory: String) {

        val laundryCategoryButtons = model.getItemsForLaundryCategory(laundryCategory)
        gridViewAdapter = GridViewAdapter(laundryCategoryButtons)

        gridLayout.apply {
            numColumns = model.getAmountOfColumnsForGridViewPerLaundryCategory(laundryCategory)
            adapter = gridViewAdapter
        }
    }
}