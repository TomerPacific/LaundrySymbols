package com.tomerpacific.laundry

import android.app.Activity
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.TooltipCompat
import java.util.*

class Utilities {

    companion object {
        fun setFont(activity: Activity, fontToSet: String, viewIdToSetFont: Int) {
            val assetManager: AssetManager = activity.assets
            val typeFace: Typeface = Typeface.createFromAsset(
                assetManager,
                String.format(Locale.US, "fonts/%s", fontToSet)
            )
            val textView: TextView = activity.findViewById(viewIdToSetFont)
            textView.setTypeface(typeFace)
        }

        fun setTooltipsAndListeners(scrollView: ScrollView) {
            val buttons: ArrayList<View> = scrollView.touchables
            for (button in buttons) {
                val tooltipText = button.contentDescription
                if (tooltipText != null) {
                    TooltipCompat.setTooltipText(button, tooltipText)
                }

                button.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {

                        val tag: String? = v?.tag as? String
                        val intent = Intent(scrollView.context, LaundrySymbolDescription::class.java)
                        intent.putExtra(SYMBOL_NAME_KEY, tooltipText)
                        intent.putExtra(SYMBOL_IMAGE_KEY, tag)
                        intent.putExtra(SYMBOL_DESCRIPTION_KEY, tooltipText)

                        scrollView.context.startActivity(intent)

                    }
                })

            }
        }

        fun setListenerForView(viewToSetListenerTo: View, onClickListener: View.OnClickListener) {
            viewToSetListenerTo.setOnClickListener(onClickListener)
        }

        fun extractImagePath(imageName : String) : String {
            var imageNameWithoutExtension : String
            val indexOfExtension : Int = imageName.indexOf(".")

            imageNameWithoutExtension = imageName.substring(0, indexOfExtension)

            val indexOfPathPrefix : Int = imageNameWithoutExtension.indexOf(DRAWABLE_ASSET_PREFIX_PATH)
            imageNameWithoutExtension = imageNameWithoutExtension.substring(indexOfPathPrefix + DRAWABLE_ASSET_PREFIX_PATH.length)

            return imageNameWithoutExtension
        }
    }

}