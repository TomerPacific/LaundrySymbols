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
            "wash_by_hand" to "Hand Wash",
            "bleach_do_not" to "Do Not Bleach",
            "bleach_allow" to "Bleaching Allowed",
            "bleach_non_chlorine" to "Bleach Non Chlorine",
            "dry_cleaning_do_not" to "Do Not Dry Clean",
            "dry_cleaning_allow" to "Dry Cleaning Allowed",
            "dry_cleaning_low_heat" to "Dry Cleaning Low Heat",
            "dry_cleaning_no_steam" to "Dry Cleaning No Steam",
            "dry_cleaning_a" to "Dry Cleaning Any Solvent",
            "dry_cleaning_p" to "Dry Cleaning Only",
            "dry_cleaning_f" to "Dry Cleaning Petroleum Solvent Only",
            "tumble_dry_low" to "Tumble Dry Low Heat",
            "tumble_dry_high" to "Tumble Dry High Heat",
            "natural_drying_hang_to_dry" to "Drip Dry",
            "natural_drying_one_line" to "Dry Flat",
            "natural_drying_hang_to_dry" to "Hang To Dry",
            "dryer_do_not_tumble_dry" to "Do Not Tumble Dry",
            "iron_do_not" to "Do Not Iron",
            "iron_allowed" to "Ironing Allowed",
            "iron_low_setting" to "Iron Low (Max 110&#xb0; Celsius)",
            "iron_medium_setting" to "Iron Medium (Max 150&#xb0; Celsius)",
            "iron_high_setting" to "Iron High (Max 200&#xb0; Celsius)",
            "iron_steam_not_allowed" to "Steaming Not Allowed"
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