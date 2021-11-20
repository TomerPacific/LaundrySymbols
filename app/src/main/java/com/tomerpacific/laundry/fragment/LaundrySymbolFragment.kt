package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.model.LaundrySymbol

class LaundrySymbolFragment : Fragment() {

    companion object {
        fun newInstance(laundrySymbol: LaundrySymbol) : LaundrySymbolFragment {
            val fragment = LaundrySymbolFragment()
            val args = Bundle()
            args.putString(LAUNDRY_SYMBOL_NAME_KEY, laundrySymbol.name)
            args.putInt(LAUNDRY_SYMBOL_RESOURCE_IDENTIFIER_KEY, laundrySymbol.drawableId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.symbol_description, container, false)
        val symbolHeader: TextView = view.findViewById(R.id.symbol_header)
        val symbolDescription: TextView = view.findViewById(R.id.symbol_description)
        val symbolImage: ImageView = view.findViewById(R.id.symbol_image)

        val symbolDescriptionText: String = this.arguments?.getString(LAUNDRY_SYMBOL_NAME_KEY) ?: return view
        val symbolResourceIdentifier: Int = this.arguments?.getInt(LAUNDRY_SYMBOL_RESOURCE_IDENTIFIER_KEY) ?: return view

        symbolHeader.text = symbolDescriptionText
        symbolDescription.text =symbolDescriptionText
        symbolImage.apply {
            contentDescription = symbolDescriptionText
            setImageResource(symbolResourceIdentifier)
        }

        return view;
    }

}