package com.tomerpacific.laundry

import android.graphics.Typeface
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.TooltipCompat

class Utilities {

    companion object {

        fun setFont(view: TextView, fontToSet: String) {
            val typeface = Typeface.createFromAsset(view.context.assets,"fonts/${fontToSet}")
            view.typeface = typeface
        }

        fun setTooltipForSymbol(imageButton: ImageButton) {
            val symbolDescription = imageButton.contentDescription
            TooltipCompat.setTooltipText(imageButton, symbolDescription)
        }

    }

}