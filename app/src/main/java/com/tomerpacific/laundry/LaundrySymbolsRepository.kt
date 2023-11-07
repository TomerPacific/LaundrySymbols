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

    fun createBleachingSymbols(context: Context) : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(context.getString(R.string.do_not_bleach_symbol), context.getString(R.string.do_not_bleach_symbol), R.drawable.bleach_do_not),
            LaundrySymbol(context.getString(R.string.bleaching_allowed_symbol), context.getString(R.string.bleaching_allowed_symbol), R.drawable.bleach_allow),
            LaundrySymbol(context.getString(R.string.bleaching_non_chlorine_symbol), context.getString(R.string.bleaching_non_chlorine_symbol), R.drawable.bleach_non_chlorine)
        )
    }

    fun createDryingSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(context.getString(R.string.do_not_dry_clean_symbol), context.getString(R.string.do_not_dry_clean_symbol), R.drawable.dry_cleaning_do_not),
            LaundrySymbol(context.getString(R.string.dry_cleaning_allowed_symbol), context.getString(R.string.dry_cleaning_allowed_symbol), R.drawable.dry_cleaning_allow),
            LaundrySymbol(context.getString(R.string.dry_cleaning_low_heat_symbol), context.getString(R.string.dry_cleaning_low_heat_symbol), R.drawable.dry_cleaning_low_heat),
            LaundrySymbol(context.getString(R.string.dry_cleaning_no_steam_symbol), context.getString(R.string.dry_cleaning_no_steam_symbol), R.drawable.dry_cleaning_no_steam),
            LaundrySymbol(context.getString(R.string.dry_cleaning_any_solvent_symbol), context.getString(R.string.dry_cleaning_any_solvent_symbol), R.drawable.dry_cleaning_a),
            LaundrySymbol(context.getString(R.string.dry_cleaning_only_symbol), context.getString(R.string.dry_cleaning_only_symbol), R.drawable.dry_cleaning_p),
            LaundrySymbol(context.getString(R.string.dry_cleaning_petroleum_solvent_only_symbol), context.getString(R.string.dry_cleaning_petroleum_solvent_only_symbol), R.drawable.dry_cleaning_f),
            LaundrySymbol(context.getString(R.string.tumble_dry_low_heat_symbol), context.getString(R.string.tumble_dry_low_heat_symbol), R.drawable.tumble_dry_low),
            LaundrySymbol(context.getString(R.string.tumble_dry_high_heat_symbol), context.getString(R.string.tumble_dry_high_heat_symbol), R.drawable.tumble_dry_high),
            LaundrySymbol(context.getString(R.string.dry_flat_symbol), context.getString(R.string.dry_flat_symbol), R.drawable.natural_drying_one_line),
            LaundrySymbol(context.getString(R.string.hang_to_dry_symbol), context.getString(R.string.hang_to_dry_symbol), R.drawable.natural_drying_hang_to_dry),
            LaundrySymbol(context.getString(R.string.do_not_tumble_dry_symbol), context.getString(R.string.do_not_tumble_dry_symbol), R.drawable.dryer_do_not_tumble_dry)
        )
    }

    fun createIroningSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(context.getString(R.string.do_not_iron_symbol), context.getString(R.string.do_not_iron_symbol), R.drawable.iron_do_not),
            LaundrySymbol(context.getString(R.string.ironing_allowed_symbol), context.getString(R.string.ironing_allowed_symbol), R.drawable.iron_allowed),
            LaundrySymbol(context.getString(R.string.iron_low_symbol), context.getString(R.string.iron_low_symbol), R.drawable.iron_low_setting),
            LaundrySymbol(context.getString(R.string.iron_medium_symbol), context.getString(R.string.iron_medium_symbol), R.drawable.iron_medium_setting),
            LaundrySymbol( context.getString(R.string.tumble_dry_high_heat_symbol),  context.getString(R.string.tumble_dry_high_heat_symbol), R.drawable.iron_high_setting),
            LaundrySymbol( context.getString(R.string.steaming_not_allowed_symbol),  context.getString(R.string.steaming_not_allowed_symbol), R.drawable.iron_steam_not_allowed)
        )
    }
}