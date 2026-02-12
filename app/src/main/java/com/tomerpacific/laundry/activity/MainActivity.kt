package com.tomerpacific.laundry.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.tomerpacific.laundry.LAUNDRY_SYMBOL_KEY
import com.tomerpacific.laundry.UPDATE_REQUEST_CODE
import com.tomerpacific.laundry.ui.screens.HowToDoLaundryScreen
import com.tomerpacific.laundry.ui.screens.LaundryCategoriesScreen
import com.tomerpacific.laundry.ui.screens.LaundryCategoryScreen
import com.tomerpacific.laundry.ui.screens.LaundrySymbolScreen
import com.tomerpacific.laundry.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val TAG : String = MainActivity::class.java.simpleName
    private var appUpdateManager : AppUpdateManager? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val uriHandler = LocalUriHandler.current

            MaterialTheme {
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
                    ) {
                        val laundryCategory =
                            it.arguments?.getInt("laundry_category") ?: return@composable

                        LaundryCategoryScreen(
                            laundryCategory = laundryCategory,
                            viewModel,
                            onSymbolClick = {
                                navController.navigate("laundrySymbol/${it.name}")
                            }
                        )
                    }
                    composable(
                        "laundrySymbol/{$LAUNDRY_SYMBOL_KEY}",
                        arguments = listOf(navArgument(LAUNDRY_SYMBOL_KEY) { type = NavType.StringType })
                    ) {
                        val laundrySymbolName = it.arguments?.getString(LAUNDRY_SYMBOL_KEY)
                        LaundrySymbolScreen(
                            viewModel = viewModel,
                            symbolName = laundrySymbolName
                        )
                    }

                    composable("howToDoLaundry") {
                        HowToDoLaundryScreen(
                            categories = viewModel.getHowToDoLaundryCategories(),
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
        }
        checkForUpdate()
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager?.apply {
            appUpdateInfo.addOnSuccessListener { result: AppUpdateInfo? ->
                    if (result?.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                       startUpdateFlowForResult(result,
                        AppUpdateType.IMMEDIATE,
                        this@MainActivity,
                        UPDATE_REQUEST_CODE
                        )
                    }
            }
        }
    }

    private fun checkForUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)

        appUpdateManager?.apply {
            appUpdateInfo.addOnSuccessListener {
                if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                        it.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        startUpdateFlowForResult(
                            it,
                            AppUpdateType.IMMEDIATE,
                            this@MainActivity,
                            UPDATE_REQUEST_CODE
                        )
                    } catch(e : Exception) {
                        e.printStackTrace()
                    }
                }
                when(it.installStatus()) {
                    InstallStatus.INSTALLED -> Log.d(TAG, "checkForUpdate update already installed")
                    InstallStatus.INSTALLING -> Log.d(TAG, "checkForUpdate update is being installed")
                    InstallStatus.DOWNLOADED -> Log.d(TAG, "checkForUpdate update downloaded")
                    InstallStatus.DOWNLOADING -> Log.d(TAG, "checkForUpdate checkForUpdate is still downloading")
                    InstallStatus.CANCELED -> Log.d(TAG, "checkForUpdate checkForUpdate has been cancelled")
                    else -> Log.e(TAG, "checkForUpdate checkForUpdate updateAvailability FAILED")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == UPDATE_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                Log.e(TAG, "onActivityResult: app download failed")
            }
        }
    }

}
