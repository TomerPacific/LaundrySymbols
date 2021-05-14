package com.tomerpacific.laundry.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tomerpacific.laundry.BANGERS_FONT
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.Utilities

class LaundryCategoryFragment : Fragment() {

    companion object {
        fun newInstance(laundryCategory: String) : LaundryCategoryFragment {
            val fragment = LaundryCategoryFragment()
            val args = Bundle()
            args.putString("laundry_category", laundryCategory)
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
        laundryCategoryTextView.text = this.arguments?.getString("laundry_category")

        Utilities.setFont(
            view,
            requireActivity(),
            BANGERS_FONT,
            R.id.laundry_category_textview
        )
        return view;
    }

}