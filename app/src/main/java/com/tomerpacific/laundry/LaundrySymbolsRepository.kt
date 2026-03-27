package com.tomerpacific.laundry

import android.content.Context
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.model.LaundryCategory
import com.tomerpacific.laundry.model.LaundrySymbol
import com.tomerpacific.laundry.model.SymbolId

class LaundrySymbolsRepository : ILaundrySymbolsRepository {

    override fun createWashingSymbols(context: Context) : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                id = SymbolId("do-not-wash"),
                name = context.getString(R.string.do_not_wash_symbol_title),
                description = context.getString(R.string.do_not_wash_symbol_description),
                drawableId = R.drawable.wash_do_not
            ),
            LaundrySymbol(
                id = SymbolId("regular-washing-allowed"),
                name = context.getString(R.string.regular_washing_allowed_symbol_title),
                description = context.getString(R.string.regular_washing_allowed_symbol_description),
                drawableId = R.drawable.washable
            ),
            LaundrySymbol(
                id = SymbolId("washing-cold"),
                name = context.getString(R.string.washing_cold_symbol_title),
                description = context.getString(R.string.washing_cold_symbol_description),
                drawableId = R.drawable.wash_30_degrees,
                temperature = 30,
                descriptionFahrenheit = context.getString(R.string.washing_cold_symbol_description_fahrenheit),
                drawableIdFahrenheit = R.drawable.wash_86_degrees
            ),
            LaundrySymbol(
                id = SymbolId("washing-warm"),
                name = context.getString(R.string.washing_warm_symbol_title),
                description = context.getString(R.string.washing_warm_symbol_description),
                drawableId = R.drawable.wash_40_degrees_warm,
                temperature = 40,
                descriptionFahrenheit = context.getString(R.string.washing_warm_symbol_description_fahrenheit),
                drawableIdFahrenheit = R.drawable.wash_104_degrees
            ),
            LaundrySymbol(
                id = SymbolId("washing-hot"),
                name = context.getString(R.string.washing_hot_symbol_title),
                description = context.getString(R.string.washing_hot_symbol_description),
                drawableId = R.drawable.wash_60_degrees_hot,
                temperature = 60,
                descriptionFahrenheit = context.getString(R.string.washing_hot_symbol_description_fahrenheit),
                drawableIdFahrenheit = R.drawable.wash_140_degrees
            ),
            LaundrySymbol(
                id = SymbolId("synthetic-cycle"),
                name = context.getString(R.string.synthetic_cycle_symbol_title),
                description = context.getString(R.string.synthetic_cycle_symbol_description),
                drawableId = R.drawable.wash_30_degrees_delicate,
                temperature = 30,
                descriptionFahrenheit = context.getString(R.string.synthetic_cycle_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                id = SymbolId("hand-wash"),
                name = context.getString(R.string.hand_wash_symbol_title),
                description = context.getString(R.string.hand_wash_symbol_description),
                drawableId = R.drawable.wash_by_hand
            )
        )
    }

    override fun createBleachingSymbols(context: Context) : List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                id = SymbolId("do-not-bleach"),
                name = context.getString(R.string.do_not_bleach_symbol_title),
                description = context.getString(R.string.do_not_bleach_symbol_description),
                drawableId = R.drawable.bleach_do_not
            ),
            LaundrySymbol(
                id = SymbolId("bleaching-allowed"),
                name = context.getString(R.string.bleaching_allowed_symbol_title),
                description = context.getString(R.string.bleaching_allowed_symbol_description),
                drawableId = R.drawable.bleach_allow
            ),
            LaundrySymbol(
                id = SymbolId("bleaching-non-chlorine"),
                name = context.getString(R.string.bleaching_non_chlorine_symbol_title),
                description = context.getString(R.string.bleaching_non_chlorine_symbol_description),
                drawableId = R.drawable.bleach_non_chlorine
            )
        )
    }

    override fun createDryingSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                id = SymbolId("do-not-dry-clean"),
                name = context.getString(R.string.do_not_dry_clean_symbol_title),
                description = context.getString(R.string.do_not_dry_clean_symbol_description),
                drawableId = R.drawable.dry_cleaning_do_not
            ),
            LaundrySymbol(
                id = SymbolId("dry-cleaning-allowed"),
                name = context.getString(R.string.dry_cleaning_allowed_symbol_title),
                description = context.getString(R.string.dry_cleaning_allowed_symbol_description),
                drawableId = R.drawable.dry_cleaning_allow
            ),
            LaundrySymbol(
                id = SymbolId("dry-cleaning-low-heat"),
                name = context.getString(R.string.dry_cleaning_low_heat_symbol_title),
                description = context.getString(R.string.dry_cleaning_low_heat_symbol_description),
                drawableId = R.drawable.dry_cleaning_low_heat
            ),
            LaundrySymbol(
                id = SymbolId("dry-cleaning-no-steam"),
                name = context.getString(R.string.dry_cleaning_no_steam_symbol_title),
                description = context.getString(R.string.dry_cleaning_no_steam_symbol_description),
                drawableId = R.drawable.dry_cleaning_no_steam
            ),
            LaundrySymbol(
                id = SymbolId("dry-cleaning-any-solvent"),
                name = context.getString(R.string.dry_cleaning_any_solvent_symbol_title),
                description = context.getString(R.string.dry_cleaning_any_solvent_symbol_description),
                drawableId = R.drawable.dry_cleaning_a
            ),
            LaundrySymbol(
                id = SymbolId("dry-cleaning-only"),
                name = context.getString(R.string.dry_cleaning_only_symbol_title),
                description = context.getString(R.string.dry_cleaning_only_symbol_description),
                drawableId = R.drawable.dry_cleaning_p
            ),
            LaundrySymbol(
                id = SymbolId("dry-cleaning-petroleum-solvent-only"),
                name = context.getString(R.string.dry_cleaning_petroleum_solvent_only_symbol_title),
                description = context.getString(R.string.dry_cleaning_petroleum_solvent_only_symbol_description),
                drawableId = R.drawable.dry_cleaning_f
            ),
            LaundrySymbol(
                id = SymbolId("tumble-dry-low-heat"),
                name = context.getString(R.string.tumble_dry_low_heat_symbol_title),
                description = context.getString(R.string.tumble_dry_low_heat_symbol_description),
                drawableId = R.drawable.tumble_dry_low
            ),
            LaundrySymbol(
                id = SymbolId("tumble-dry-high-heat"),
                name = context.getString(R.string.tumble_dry_high_heat_symbol_title),
                description = context.getString(R.string.tumble_dry_high_heat_symbol_description),
                drawableId = R.drawable.tumble_dry_high
            ),
            LaundrySymbol(
                id = SymbolId("dry-flat"),
                name = context.getString(R.string.dry_flat_symbol_title),
                description = context.getString(R.string.dry_flat_symbol_description),
                drawableId = R.drawable.natural_drying_one_line
            ),
            LaundrySymbol(
                id = SymbolId("hang-to-dry"),
                name = context.getString(R.string.hang_to_dry_symbol_title),
                description = context.getString(R.string.hang_to_dry_symbol_description),
                drawableId = R.drawable.natural_drying_hang_to_dry
            ),
            LaundrySymbol(
                id = SymbolId("do-not-tumble-dry"),
                name = context.getString(R.string.do_not_tumble_dry_symbol_title),
                description = context.getString(R.string.do_not_tumble_dry_symbol_description),
                drawableId = R.drawable.dryer_do_not_tumble_dry
            )
        )
    }

    override fun createIroningSymbols(context: Context): List<LaundrySymbol> {
        return listOf(
            LaundrySymbol(
                id = SymbolId("do-not-iron"),
                name = context.getString(R.string.do_not_iron_symbol_title),
                description = context.getString(R.string.do_not_iron_symbol_description),
                drawableId = R.drawable.iron_do_not
            ),
            LaundrySymbol(
                id = SymbolId("ironing-allowed"),
                name = context.getString(R.string.ironing_allowed_symbol_title),
                description = context.getString(R.string.ironing_allowed_symbol_description),
                drawableId = R.drawable.iron_allowed
            ),
            LaundrySymbol(
                id = SymbolId("iron-low"),
                name = context.getString(R.string.iron_low_symbol_title),
                description = context.getString(R.string.iron_low_symbol_description),
                drawableId = R.drawable.iron_low_setting,
                temperature = 110,
                descriptionFahrenheit = context.getString(R.string.iron_low_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                id = SymbolId("iron-medium"),
                name = context.getString(R.string.iron_medium_symbol_title),
                description = context.getString(R.string.iron_medium_symbol_description),
                drawableId = R.drawable.iron_medium_setting,
                temperature = 150,
                descriptionFahrenheit = context.getString(R.string.iron_medium_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                id = SymbolId("iron-high"),
                name = context.getString(R.string.iron_high_symbol_title),
                description = context.getString(R.string.iron_high_symbol_description),
                drawableId = R.drawable.iron_high_setting,
                temperature = 200,
                descriptionFahrenheit = context.getString(R.string.iron_high_symbol_description_fahrenheit)
            ),
            LaundrySymbol(
                id = SymbolId("steaming-not-allowed"),
                name = context.getString(R.string.steaming_not_allowed_symbol_title),
                description = context.getString(R.string.steaming_not_allowed_symbol_description),
                drawableId = R.drawable.iron_steam_not_allowed
            )
        )
    }


    override fun createHowToDoLaundryCategories(): List<HowToDoLaundryCategory> {
        return listOf(
            HowToDoLaundryCategory(
                labelResId = R.string.separating_laundry,
                descriptionResId = R.string.separating__laundry_description,
                drawableId = R.drawable.laundry_hamper,
                contentDescriptionResId = R.string.laundry_hamper_cd
            ),
            HowToDoLaundryCategory(
                labelResId = R.string.treating_stains,
                descriptionResId = R.string.treating_stains_description,
                drawableId = R.drawable.stain_removal,
                contentDescriptionResId = R.string.stain_removal_cd
            ),
            HowToDoLaundryCategory(
                labelResId = R.string.load_detergent,
                descriptionResId = R.string.loading_detergent_description,
                drawableId = R.drawable.loading_detergent,
                contentDescriptionResId = R.string.load_detergent_cd
            ),
            HowToDoLaundryCategory(
                labelResId = R.string.using_bleach,
                descriptionResId = R.string.using_bleach_description,
                drawableId = R.drawable.bleach,
                contentDescriptionResId = R.string.using_bleach_cd
            ),
            HowToDoLaundryCategory(
                labelResId = R.string.adding_fabric_softener,
                descriptionResId = R.string.fabric_softener_description,
                drawableId = R.drawable.fabric_softener,
                contentDescriptionResId = R.string.adding_fabric_softener_cd
            ),
            HowToDoLaundryCategory(
                labelResId = R.string.wash_cycle,
                descriptionResId = R.string.wash_cycle_description,
                drawableId = R.drawable.wash_cycle,
                contentDescriptionResId = R.string.wash_cycle_cd
            ),
            HowToDoLaundryCategory(
                labelResId = R.string.wash_temperature,
                descriptionResId = R.string.wash_temperature_description,
                drawableId = R.drawable.wash_temperature,
                contentDescriptionResId = R.string.wash_temperature_cd
            )
        )
    }

    override fun createLaundryCategories(): List<LaundryCategory> {
        return listOf(
            LaundryCategory(
                labelResId = R.string.washing,
                drawableId = R.drawable.washable,
                contentDescriptionResId = R.string.washing_symbol,
                testTag = "washing category"
            ),
            LaundryCategory(
                labelResId = R.string.bleaching,
                drawableId = R.drawable.bleach_allow,
                contentDescriptionResId = R.string.bleaching_symbol,
                testTag = "bleaching category"
            ),
            LaundryCategory(
                labelResId = R.string.drying,
                drawableId = R.drawable.dry_cleaning_allow,
                contentDescriptionResId = R.string.drying_symbol,
                testTag = "drying category"
            ),
            LaundryCategory(
                labelResId = R.string.ironing,
                drawableId = R.drawable.iron_allowed,
                contentDescriptionResId = R.string.ironing_symbol,
                testTag = "ironing category"
            )
        )
    }

    override fun createLaundryCategoryItems(context: Context): Map<Int, List<LaundrySymbol>> {
        return mapOf(
            R.string.washing to createWashingSymbols(context),
            R.string.bleaching to createBleachingSymbols(context),
            R.string.drying to createDryingSymbols(context),
            R.string.ironing to createIroningSymbols(context)
        )
    }
}
