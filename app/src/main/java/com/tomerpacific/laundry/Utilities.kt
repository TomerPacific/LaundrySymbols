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
        fun setFont(activity: Activity, fontToSet: String, viewIdToSetFont: Int) {

            activity.findViewById<TextView>(viewIdToSetFont).apply {
                val assetManager: AssetManager = activity.assets
                val typeFace: Typeface = Typeface.createFromAsset(
                    assetManager,
                    String.format(Locale.US, "fonts/%s", fontToSet)
                )
                this.typeface = typeFace
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

        fun setListenerForView(viewToSetListenerTo: View, onClickListener: View.OnClickListener) {
            viewToSetListenerTo.setOnClickListener(onClickListener)
        }

        fun extractImagePath(imageName : String) : String {
            val imageName : String = imageName.replace("res/drawable-xxhdpi-v4/", "")
            val indexOfExtension : Int = imageName.indexOf(".")

            return imageName.substring(0, indexOfExtension)
        }

    }

}