package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.tomerpacific.laundry.LAUNDRY_SYMBOL_NAME_KEY
import com.tomerpacific.laundry.LAUNDRY_SYMBOL_RESOURCE_IDENTIFIER_KEY
import com.tomerpacific.laundry.model.LaundrySymbol
import com.tomerpacific.laundry.ui.screens.LaundrySymbolScreen

class LaundrySymbolFragment: Fragment() {

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
        var symbolDescriptionText = ""
        var symbolResourceIdentifier = 0
        arguments?.apply {
            this.getString(LAUNDRY_SYMBOL_NAME_KEY)?.let {
                symbolDescriptionText = it
            }
            symbolResourceIdentifier = this.getInt(LAUNDRY_SYMBOL_RESOURCE_IDENTIFIER_KEY)
        }

        return ComposeView(requireContext()).apply {
            setContent {
                LaundrySymbolScreen(
                    symbolName = symbolDescriptionText,
                    symbolDrawableId = symbolResourceIdentifier
                )
            }
        }
    }

}