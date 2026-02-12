package com.tomerpacific.laundry

import android.content.Context
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol

class LaundrySymbolsRepository {

    fun createWashingSymbols(context: Context) : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                name = context.getString(R.string.do_not_wash_symbol_title),
                description = context.getString(R.string.do_not_wash_symbol_description),
                drawableId = R.drawable.wash_do_not
            ),
            LaundrySymbol(
                name = context.getString(R.string.regular_washing_allowed_symbol_title),
                description = context.getString(R.string.regular_washing_allowed_symbol_description),
                drawableId = R.drawable.washable
            ),
            LaundrySymbol(
                name = context.getString(R.string.washing_cold_symbol_description),
                description = context.getString(R.string.washing_cold_symbol_description),
                drawableId = R.drawable.wash_30_degrees,
                temperature = 30,
                nameFahrenheit = context.getString(R.string.washing_cold_symbol_description_fahrenheit),
                descriptionFahrenheit = context.getString(R.string.washing_cold_symbol_description_fahrenheit),
                drawableIdFahrenheit = R.drawable.wash_86_degrees
            ),
            LaundrySymbol(
                name = context.getString(R.string.washing_warm_symbol_description),
                description = context.getString(R.string.washing_warm_symbol_description),
                drawableId = R.drawable.wash_40_degrees_warm,
                temperature = 40,
                nameFahrenheit = context.getString(R.string.washing_warm_symbol_description_fahrenheit),
                descriptionFahrenheit = context.getString(R.string.washing_warm_symbol_description_fahrenheit),
                drawableIdFahrenheit = R.drawable.wash_104_degrees
            ),
            LaundrySymbol(
                name = context.getString(R.string.washing_hot_symbol_description),
                description = context.getString(R.string.washing_hot_symbol_description),
                drawableId = R.drawable.wash_60_degrees_hot,
                temperature = 60,
                nameFahrenheit = context.getString(R.string.washing_hot_symbol_description_fahrenheit),
                descriptionFahrenheit = context.getString(R.string.washing_hot_symbol_description_fahrenheit),
                drawableIdFahrenheit = R.drawable.wash_140_degrees
            ),
            LaundrySymbol(
                name = context.getString(R.string.synthetic_cycle_symbol_description),
                description = context.getString(R.string.synthetic_cycle_symbol_description),
                drawableId = R.drawable.wash_30_degrees_delicate,
                temperature = 30,
                nameFahrenheit = context.getString(R.string.synthetic_cycle_symbol_description_fahrenheit),
                descriptionFahrenheit = context.getString(R.string.synthetic_cycle_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                name = context.getString(R.string.hand_wash_symbol_title),
                description = context.getString(R.string.hand_wash_symbol_description),
                drawableId = R.drawable.wash_by_hand
            )
        )
    }

    fun createBleachingSymbols(context: Context) : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                name = context.getString(R.string.do_not_bleach_symbol_title),
                description = context.getString(R.string.do_not_bleach_symbol_description),
                drawableId = R.drawable.bleach_do_not
            ),
            LaundrySymbol(
                name = context.getString(R.string.bleaching_allowed_symbol_title),
                description = context.getString(R.string.bleaching_allowed_symbol_description),
                drawableId = R.drawable.bleach_allow
            ),
            LaundrySymbol(
                name = context.getString(R.string.bleaching_non_chlorine_symbol_title),
                description = context.getString(R.string.bleaching_non_chlorine_symbol_description),
                drawableId = R.drawable.bleach_non_chlorine
            )
        )
    }

    fun createDryingSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                name = context.getString(R.string.do_not_dry_clean_symbol_title),
                description = context.getString(R.string.do_not_dry_clean_symbol_description),
                drawableId = R.drawable.dry_cleaning_do_not
            ),
            LaundrySymbol(
                name = context.getString(R.string.dry_cleaning_allowed_symbol_title),
                description = context.getString(R.string.dry_cleaning_allowed_symbol_description),
                drawableId = R.drawable.dry_cleaning_allow
            ),
            LaundrySymbol(
                name = context.getString(R.string.dry_cleaning_low_heat_symbol_title),
                description = context.getString(R.string.dry_cleaning_low_heat_symbol_description),
                drawableId = R.drawable.dry_cleaning_low_heat
            ),
            LaundrySymbol(
                name = context.getString(R.string.dry_cleaning_no_steam_symbol_title),
                description = context.getString(R.string.dry_cleaning_no_steam_symbol_description),
                drawableId = R.drawable.dry_cleaning_no_steam
            ),
            LaundrySymbol(
                name = context.getString(R.string.dry_cleaning_any_solvent_symbol_title),
                description = context.getString(R.string.dry_cleaning_any_solvent_symbol_description),
                drawableId = R.drawable.dry_cleaning_a
            ),
            LaundrySymbol(
                name = context.getString(R.string.dry_cleaning_only_symbol_title),
                description = context.getString(R.string.dry_cleaning_only_symbol_description),
                drawableId = R.drawable.dry_cleaning_p
            ),
            LaundrySymbol(
                name = context.getString(R.string.dry_cleaning_petroleum_solvent_only_symbol_title),
                description = context.getString(R.string.dry_cleaning_petroleum_solvent_only_symbol_description),
                drawableId = R.drawable.dry_cleaning_f
            ),
            LaundrySymbol(
                name = context.getString(R.string.tumble_dry_low_heat_symbol_title),
                description = context.getString(R.string.tumble_dry_low_heat_symbol_description),
                drawableId = R.drawable.tumble_dry_low
            ),
            LaundrySymbol(
                name = context.getString(R.string.tumble_dry_high_heat_symbol_title),
                description = context.getString(R.string.tumble_dry_high_heat_symbol_description),
                drawableId = R.drawable.tumble_dry_high
            ),
            LaundrySymbol(
                name = context.getString(R.string.dry_flat_symbol_title),
                description = context.getString(R.string.dry_flat_symbol_description),
                drawableId = R.drawable.natural_drying_one_line
            ),
            LaundrySymbol(
                name = context.getString(R.string.hang_to_dry_symbol_title),
                description = context.getString(R.string.hang_to_dry_symbol_description),
                drawableId = R.drawable.natural_drying_hang_to_dry
            ),
            LaundrySymbol(
                name = context.getString(R.string.do_not_tumble_dry_symbol_title),
                description = context.getString(R.string.do_not_tumble_dry_symbol_description),
                drawableId = R.drawable.dryer_do_not_tumble_dry
            )
        )
    }

    fun createIroningSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                name = context.getString(R.string.do_not_iron_symbol_title),
                description = context.getString(R.string.do_not_iron_symbol_description),
                drawableId = R.drawable.iron_do_not
            ),
            LaundrySymbol(
                name = context.getString(R.string.ironing_allowed_symbol_title),
                description = context.getString(R.string.ironing_allowed_symbol_description),
                drawableId = R.drawable.iron_allowed
            ),
            LaundrySymbol(
                name = context.getString(R.string.iron_low_symbol_description),
                description = context.getString(R.string.iron_low_symbol_description),
                drawableId = R.drawable.iron_low_setting,
                temperature = 110,
                nameFahrenheit = context.getString(R.string.iron_low_symbol_description_fahrenheit),
                descriptionFahrenheit = context.getString(R.string.iron_low_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                name = context.getString(R.string.iron_medium_symbol_description),
                description = context.getString(R.string.iron_medium_symbol_description),
                drawableId = R.drawable.iron_medium_setting,
                temperature = 150,
                nameFahrenheit = context.getString(R.string.iron_medium_symbol_description_fahrenheit),
                descriptionFahrenheit = context.getString(R.string.iron_medium_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                name = context.getString(R.string.iron_high_symbol_description),
                description = context.getString(R.string.iron_high_symbol_description),
                drawableId = R.drawable.iron_high_setting,
                temperature = 200,
                nameFahrenheit = context.getString(R.string.iron_high_symbol_description_fahrenheit),
                descriptionFahrenheit = context.getString(R.string.iron_high_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                name = context.getString(R.string.steaming_not_allowed_symbol_title),
                description = context.getString(R.string.steaming_not_allowed_symbol_description),
                drawableId = R.drawable.iron_steam_not_allowed
            )
        )
    }


    fun createHowToDoLaundryCategories(): List<HowToDoLaundryCategory> {
        return listOf(
            HowToDoLaundryCategory(R.string.separating_laundry,
                R.string.separating__laundry_description,
                R.drawable.laundry_hamper,
                R.string.laundry_hamper_cd),
            HowToDoLaundryCategory(R.string.treating_stains,
                R.string.treating_stains_description,
                R.drawable.stain_removal,
                R.string.stain_removal_cd),
            HowToDoLaundryCategory(R.string.load_detergent,
                R.string.loading_detergent_description,
                R.drawable.loading_detergent,
                R.string.load_detergent_cd),
            HowToDoLaundryCategory(R.string.using_bleach,
                R.string.using_bleach_description,
                R.drawable.bleach,
                R.string.using_bleach_cd),
            HowToDoLaundryCategory(R.string.adding_fabric_softener,
                R.string.fabric_softener_description,
                R.drawable.fabric_softener,
                R.string.adding_fabric_softener_cd),
            HowToDoLaundryCategory(R.string.wash_cycle,
                R.string.wash_cycle_description,
                R.drawable.wash_cycle,
                R.string.wash_cycle_cd),
            HowToDoLaundryCategory(R.string.wash_temperature,
                R.string.wash_temperature_description,
                R.drawable.wash_temperature,
                R.string.wash_temperature_cd)
        )
    }

    fun createLaundryCategoryItems(context: Context): Map<Int, List<LaundrySymbol>> {
        return mapOf(
            R.string.washing to createWashingSymbols(context),
            R.string.bleaching to createBleachingSymbols(context),
            R.string.drying to createDryingSymbols(context),
            R.string.ironing to createIroningSymbols(context)
        )
    }
}