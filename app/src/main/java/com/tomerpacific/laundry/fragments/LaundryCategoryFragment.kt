package com.tomerpacific.laundry.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.tomerpacific.laundry.*

class LaundryCategoryFragment : Fragment() {

    private lateinit var gridViewAdapter: GridViewAdapter

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
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_laundry_category, container, false)
        val laundryCategoryTextView : TextView = view.findViewById<TextView>(R.id.laundry_category_textview)
        val laundryCategory : String? = this.arguments?.getString(LAUNDRY_CATEGORY_KEY)
        laundryCategoryTextView.text = laundryCategory

        Utilities.setFont(
            view,
            requireActivity(),
            BANGERS_FONT,
            R.id.laundry_category_textview
        )

        val gridLayout: GridView = view.findViewById(R.id.grid_layout)
        setupLayoutForLaundryCategory(laundryCategory, gridLayout)
        return view;
    }

    private fun setupLayoutForLaundryCategory(laundryCategory: String?, gridLayout: GridView) {
        val buttonsToGenerate: List<String> = when(laundryCategory) {
            "Washing" -> listOf("wash_do_not",
                "washable",
                "wash_30_degrees",
                "wash_40_degrees_warm",
                "wash_60_degrees_hot",
                "wash_30_degrees_delicate",
                "wash_30_double_line",
                "wash_by_hand"
            )
            else -> listOf()
        }

        gridViewAdapter = GridViewAdapter(buttonsToGenerate)
        gridLayout.adapter = gridViewAdapter
    }


}