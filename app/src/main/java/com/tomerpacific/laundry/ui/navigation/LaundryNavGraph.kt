package com.tomerpacific.laundry.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tomerpacific.laundry.LAUNDRY_SYMBOL_KEY
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.ui.screens.HowToDoLaundryScreen
import com.tomerpacific.laundry.ui.screens.LaundryCategoriesScreen
import com.tomerpacific.laundry.ui.screens.LaundryCategoryScreen
import com.tomerpacific.laundry.ui.screens.LaundrySymbolScreen
import com.tomerpacific.laundry.viewmodel.MainUiEvent
import com.tomerpacific.laundry.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LaundryNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val validCategoryIds = remember(viewModel.laundryCategories) {
        viewModel.laundryCategories.map { it.labelResId }.toSet()
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvents.collectLatest { event ->
            when (event) {
                is MainUiEvent.ShowNoBrowserError -> {
                    Toast.makeText(context, context.getString(R.string.error_opening_link), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    NavHost(navController = navController, startDestination = "laundryCategories") {
        composable("laundryCategories") {
            LaundryCategoriesScreen(
                categories = viewModel.laundryCategories,
                onCategoryClick = {
                    navController.navigate("laundryCategory/$it")
                },
                onLearnMoreClick = {
                    navController.navigate("howToDoLaundry")
                },
                onVersionClick = {
                    viewModel.handleClickOnVersion(uriHandler)
                }
            )
        }
        composable(
            "laundryCategory/{laundry_category}",
            arguments = listOf(navArgument("laundry_category") { type = NavType.IntType })
        ) { entry ->
            val laundryCategory = entry.arguments?.getInt("laundry_category") ?: 0

            if (laundryCategory in validCategoryIds) {
                LaundryCategoryScreen(
                    laundryCategory = laundryCategory,
                    viewModel = viewModel,
                    onSymbolClick = {
                        navController.navigate("laundrySymbol/${it.id.value}")
                    }
                )
            } else {
                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }
            }
        }
        composable(
            "laundrySymbol/{$LAUNDRY_SYMBOL_KEY}",
            arguments = listOf(navArgument(LAUNDRY_SYMBOL_KEY) { type = NavType.StringType })
        ) {
            val laundrySymbolId = it.arguments?.getString(LAUNDRY_SYMBOL_KEY)
            LaundrySymbolScreen(
                viewModel = viewModel,
                symbolId = laundrySymbolId
            )
        }

        composable("howToDoLaundry") {
            HowToDoLaundryScreen(
                categories = viewModel.howToDoLaundryCategories,
                selectedCategory = viewModel.selectedDrawerItem.value,
                onCategoryClick = {
                    viewModel.handleClickOnHowToDoLaundryCategories(it)
                },
                onHomeClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
