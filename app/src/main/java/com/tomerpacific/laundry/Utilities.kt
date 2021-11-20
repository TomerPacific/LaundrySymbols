package com.tomerpacific.laundry

import android.app.Activity
import android.content.res.AssetManager
import android.graphics.Typeface
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.TooltipCompat
import java.util.*

class Utilities {

    companion object {

        fun setFont(activity: Activity, fontToSet: String, viewIdToSetFont: Int) {

            activity.findViewById<TextView>(viewIdToSetFont).apply {
                val assetManager: AssetManager = activity.assets
                val typeFace: Typeface = Typeface.createFromAsset(
                    assetManager,
                    String.format(Locale.US, "fonts/%s", fontToSet)
                )
                this?.typeface = typeFace
            }
        }

        fun setTooltipForSymbol(imageButton: ImageButton) {
            val symbolDescription = imageButton.contentDescription
            TooltipCompat.setTooltipText(imageButton, symbolDescription)
        }

        fun getLaundryCategoryDrawableId(laundryCategoryName: String) : Int {
            return when (laundryCategoryName) {
                LAUNDRY_CATEGORY_WASHING -> R.id.washing_imageview
                LAUNDRY_CATEGORY_BLEACHING -> R.id.bleaching_imageview
                LAUNDRY_CATEGORY_DRYING -> R.id.drying_imageview
                LAUNDRY_CATEGORY_IRONING -> R.id.ironing_imageview
                else -> -1
            }
        }

        fun getLaundryCategoryTextviewId(laundryCategoryName: String) : Int {
            return when (laundryCategoryName) {
                LAUNDRY_CATEGORY_WASHING -> R.id.washing_textview
                LAUNDRY_CATEGORY_BLEACHING -> R.id.bleaching_textview
                LAUNDRY_CATEGORY_DRYING -> R.id.drying_textview
                LAUNDRY_CATEGORY_IRONING -> R.id.ironing_textview
                else -> -1
            }
        }

    }

}