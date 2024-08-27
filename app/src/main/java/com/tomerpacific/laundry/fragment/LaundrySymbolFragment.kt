package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.model.LaundrySymbol

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
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Row(Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            symbolDescriptionText,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(Modifier.align(Alignment.CenterHorizontally)) {
                        Image(
                            painter = painterResource(id = symbolResourceIdentifier),
                            contentDescription = symbolDescriptionText,
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                        )
                    }
                    Row(Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            symbolDescriptionText,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

}