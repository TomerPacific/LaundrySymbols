package com.tomerpacific.laundry.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
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
import com.tomerpacific.laundry.viewmodel.MainViewModel

@Composable
fun LaundryNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    val uriHandler = LocalUriHandler.current

    NavHost(navController = navController, startDestination = "laundryCategories") {
        composable("laundryCategories") {
            LaundryCategoriesScreen(
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
            val validCategories = listOf(R.string.washing, R.string.bleaching, R.string.drying, R.string.ironing)

            if (laundryCategory in validCategories) {
                LaundryCategoryScreen(
                    laundryCategory = laundryCategory,
                    viewModel = viewModel,
                    onSymbolClick = {
                        navController.navigate("laundrySymbol/${it.id}")
                    }
                )
            } else {
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(stringResource(id = R.string.category_not_found))
                    }
                }
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
