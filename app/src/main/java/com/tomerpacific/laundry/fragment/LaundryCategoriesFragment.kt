package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tomerpacific.laundry.BANGERS_FONT
import com.tomerpacific.laundry.BuildConfig
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.Utilities

class LaundryCategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_laundry_categories, container, false)
        setFontAndVersion(view)
        setClickListenersForButtons(view)
        return view;
    }

    private fun setFontAndVersion(view: View) {
        Utilities.setFont(
            view,
            requireActivity(),
            BANGERS_FONT,
            R.id.textView
        )

        view.findViewById<TextView>(R.id.app_version).apply {
            this?.text = getString(
                R.string.app_version,
                BuildConfig.VERSION_NAME
            )
        }
    }

    private fun setClickListenersForButtons(view: View) {

        view.findViewById<LinearLayout>(R.id.washing).apply {
            val fragment: LaundryCategoryFragment = LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.washing)!!)
            setOnClickListener{
                fragmentManager?.beginTransaction()?.
                replace(R.id.fragment_container_view, fragment)?.
                addToBackStack(null)?.commit()
            }
        }

        view.findViewById<LinearLayout>(R.id.bleaching).apply {
            val fragment: LaundryCategoryFragment = LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.bleaching)!!)
            setOnClickListener{
                fragmentManager?.beginTransaction()?.
                replace(R.id.fragment_container_view, fragment)?.
                addToBackStack(null)?.commit()
            }
        }

        view.findViewById<LinearLayout>(R.id.drying).apply {
            val fragment: LaundryCategoryFragment = LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.drying)!!)
            setOnClickListener{
                fragmentManager?.beginTransaction()?.
                replace(R.id.fragment_container_view, fragment)?.
                addToBackStack(null)?.commit()
            }
        }

        view.findViewById<LinearLayout>(R.id.ironing).apply {
            val fragment: LaundryCategoryFragment = LaundryCategoryFragment.newInstance(activity?.resources?.getString(R.string.ironing)!!)
            setOnClickListener{
                fragmentManager?.beginTransaction()?.
                replace(R.id.fragment_container_view, fragment)?.
                addToBackStack(null)?.commit()
            }
        }

    }
}