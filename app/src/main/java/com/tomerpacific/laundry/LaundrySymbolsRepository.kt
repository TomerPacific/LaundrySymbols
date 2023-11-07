package com.tomerpacific.laundry

import android.content.Context
import com.tomerpacific.laundry.model.LaundrySymbol

class LaundrySymbolsRepository {

    fun createWashingSymbols(context: Context) : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(context.getString(R.string.do_not_wash_symbol), context.getString(R.string.do_not_wash_symbol), R.drawable.wash_do_not),
            LaundrySymbol(context.getString(R.string.regular_washing_allowed_symbol), context.getString(R.string.regular_washing_allowed_symbol), R.drawable.washable),
            LaundrySymbol(context.getString(R.string.washing_cold_symbol), context.getString(R.string.washing_cold_symbol), R.drawable.wash_30_degrees),
            LaundrySymbol(context.getString(R.string.washing_warm_symbol), context.getString(R.string.washing_warm_symbol), R.drawable.wash_40_degrees_warm),
            LaundrySymbol(context.getString(R.string.washing_hot_symbol), context.getString(R.string.washing_hot_symbol), R.drawable.wash_60_degrees_hot),
            LaundrySymbol(context.getString(R.string.synthetic_cycle_symbol), context.getString(R.string.synthetic_cycle_symbol), R.drawable.wash_30_degrees_delicate),
            LaundrySymbol(context.getString(R.string.hand_wash_symbol), context.getString(R.string.hand_wash_symbol), R.drawable.wash_by_hand)
        )
    }

    fun createBleachingSymbols() : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol("Do Not Bleach", "Do Not Bleach", R.drawable.bleach_do_not),
            LaundrySymbol("Bleaching Allowed", "Bleaching Allowed", R.drawable.bleach_allow),
            LaundrySymbol("Bleach Non Chlorine", "Bleach Non Chlorine", R.drawable.bleach_non_chlorine)
        )
    }

    fun createDryingSymbols(): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol("Do Not Dry Clean", "Do Not Dry Clean", R.drawable.dry_cleaning_do_not),
            LaundrySymbol("Dry Cleaning Allowed", "Dry Cleaning Allowed", R.drawable.dry_cleaning_allow),
            LaundrySymbol("Dry Cleaning Low Heat", "Dry Cleaning Low Heat", R.drawable.dry_cleaning_low_heat),
            LaundrySymbol("Dry Cleaning No Steam", "Dry Cleaning No Steam", R.drawable.dry_cleaning_no_steam),
            LaundrySymbol("Dry Cleaning Any Solvent", "Dry Cleaning Any Solvent", R.drawable.dry_cleaning_a),
            LaundrySymbol("Dry Cleaning Only", "Dry Cleaning Only", R.drawable.dry_cleaning_p),
            LaundrySymbol("Dry Cleaning Petroleum Solvent Only", "Dry Cleaning Petroleum Solvent Only", R.drawable.dry_cleaning_f),
            LaundrySymbol("Tumble Dry Low Heat", "Tumble Dry Low Heat", R.drawable.tumble_dry_low),
            LaundrySymbol("Tumble Dry High Heat", "Tumble Dry High Heat", R.drawable.tumble_dry_high),
            LaundrySymbol("Dry Flat", "Dry Flat", R.drawable.natural_drying_one_line),
            LaundrySymbol("Hang To Dry", "Hang To Dry", R.drawable.natural_drying_hang_to_dry),
            LaundrySymbol("Do Not Tumble Dry", "Do Not Tumble Dry", R.drawable.dryer_do_not_tumble_dry)
        )
    }

    fun createIroningSymbols(): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol("Do Not Iron", "Do Not Iron", R.drawable.iron_do_not),
            LaundrySymbol("Ironing Allowed", "Ironing Allowed", R.drawable.iron_allowed),
            LaundrySymbol("Iron Low (Max 110\u00B0 Celsius)", "Iron Low (Max 110\u00B0 Celsius)", R.drawable.iron_low_setting),
            LaundrySymbol("Iron Medium (Max 150\u00B0 Celsius)", "Iron Medium (Max 150\u00B0 Celsius)", R.drawable.iron_medium_setting),
            LaundrySymbol( "Iron High (Max 200\u00B0 Celsius)",  "Iron High (Max 200\u00B0 Celsius)", R.drawable.iron_high_setting),
            LaundrySymbol( "Steaming Not Allowed",  "Steaming Not Allowed", R.drawable.iron_steam_not_allowed)
        )
    }
}