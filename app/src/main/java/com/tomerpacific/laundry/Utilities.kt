package com.tomerpacific.laundry

import android.app.Activity
import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.TooltipCompat
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
            "iron_low_setting" to "Iron Low (Max 110\u00B0 Celsius)",
            "iron_medium_setting" to "Iron Medium (Max 150\u00B0 Celsius)",
            "iron_high_setting" to "Iron High (Max 200\u00B0 Celsius)",
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

        fun setTooltipForSymbol(imageButton: ImageButton) {
            val symbolDescription = imageButton.contentDescription
            TooltipCompat.setTooltipText(imageButton, symbolDescription)
        }

        fun getSymbolDescription(symbolName: String): String {
            return symbolDescriptionMap[symbolName]!!
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