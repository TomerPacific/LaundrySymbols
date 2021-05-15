package com.tomerpacific.laundry

import android.app.Activity
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.TooltipCompat
import com.tomerpacific.laundry.activities.LaundrySymbolDescriptionActivity
import java.util.*

class Utilities {

    companion object {

        private val symbolDescriptionMap = mapOf<String, String>(
            "wash_do_not" to "Do Not Wash",
            "washable" to "Regular Washing Allowed",
            "wash_30_degrees" to "Washing Cold",
            "wash_40_degrees_warm" to "Washing Warm",
            "wash_60_degrees_hot" to "Washing Hot",
            "wash_30_degrees_delicate" to "Synthetic Cycle",
            "wash_30_double_line" to "Wool/Gentle Cycle",
            "wash_by_hand" to "Hand Wash"
        )


        fun setFont(view: View?, activity: Activity, fontToSet: String, viewIdToSetFont: Int) {

            view?.findViewById<TextView>(viewIdToSetFont).apply {
                val assetManager: AssetManager = activity.assets
                val typeFace: Typeface = Typeface.createFromAsset(
                    assetManager,
                    String.format(Locale.US, "fonts/%s", fontToSet)
                )
                this?.typeface = typeFace
            }
        }

        fun setTooltipsAndListeners(view: View) {
            val buttons: ArrayList<View> = view.touchables
            for (button in buttons) {

                val tooltipText = button.contentDescription?.apply {
                    TooltipCompat.setTooltipText(button, this)
                }

                button.setOnClickListener {
                            val tag: String? = it.tag as String
                            Intent(view.context, LaundrySymbolDescriptionActivity::class.java).apply {
                                putExtra(SYMBOL_NAME_KEY, tooltipText)
                                putExtra(SYMBOL_IMAGE_KEY, tag)
                                putExtra(SYMBOL_DESCRIPTION_KEY, tooltipText)

                                view.context.startActivity(this)
                            }
                    }
            }
        }

        fun extractImagePath(imageName : String) : String {
            val imageName : String = imageName.replace("res/drawable-xxhdpi-v4/", "")
            val indexOfExtension : Int = imageName.indexOf(".")

            return imageName.substring(0, indexOfExtension)
        }

        fun getSymbolDescription(symbolName: String): String {
            return symbolDescriptionMap[symbolName]!!
        }

    }

}