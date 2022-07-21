package com.tomerpacific.laundry

import android.widget.ImageButton
import androidx.appcompat.widget.TooltipCompat

class Utilities {

    companion object {

        fun setTooltipForSymbol(imageButton: ImageButton) {
            val symbolDescription = imageButton.contentDescription
            TooltipCompat.setTooltipText(imageButton, symbolDescription)
        }

    }

}