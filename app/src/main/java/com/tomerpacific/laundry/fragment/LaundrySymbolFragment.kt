package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tomerpacific.laundry.*

class LaundrySymbolFragment : Fragment() {

    companion object {
        fun newInstance(symbolName: String, resourceIdentifier: Int) : LaundrySymbolFragment {
            val fragment = LaundrySymbolFragment()
            val args = Bundle()
            args.putString(LAUNDRY_SYMBOL_NAME_KEY, symbolName)
            args.putInt(LAUNDRY_SYMBOL_RESOURCE_IDENTIFIER_KEY, resourceIdentifier)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.symbol_description, container, false)
        val symbolHeader: TextView = view.findViewById(R.id.symbol_header)
        val symbolDescription: TextView = view.findViewById(R.id.symbol_description)
        val symbolImage: ImageView = view.findViewById(R.id.symbol_image)

        val symbolText : String? = this.arguments?.getString(LAUNDRY_SYMBOL_NAME_KEY)
        val symbolDescriptionText: String = Utilities.getSymbolDescription(symbolText!!)
        val symbolResourceIdentifier: Int? = this.arguments?.getInt(LAUNDRY_SYMBOL_RESOURCE_IDENTIFIER_KEY)

        symbolHeader.text = symbolDescriptionText
        symbolDescription.text =symbolDescriptionText
        symbolImage.contentDescription = symbolDescriptionText
        symbolImage.setImageResource(symbolResourceIdentifier!!)


        return view;
    }

}