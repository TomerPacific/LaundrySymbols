package com.tomerpacific.laundry.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.tomerpacific.laundry.ui.navigation.LaundryNavGraph
import com.tomerpacific.laundry.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val TAG : String = MainActivity::class.java.simpleName
    private var appUpdateManager : AppUpdateManager? = null
    private val viewModel: MainViewModel by viewModels()

    private val installStateUpdatedListener = InstallStateUpdatedListener { state ->
        when (state.installStatus()) {
            InstallStatus.DOWNLOADED -> Log.d(TAG, "installStateUpdatedListener: update downloaded")
            InstallStatus.INSTALLED -> Log.d(TAG, "installStateUpdatedListener: update installed")
            InstallStatus.INSTALLING -> Log.d(TAG, "installStateUpdatedListener: update installing")
            InstallStatus.DOWNLOADING -> Log.d(TAG, "installStateUpdatedListener: update downloading")
            InstallStatus.CANCELED -> Log.d(TAG, "installStateUpdatedListener: update cancelled")
        }
    }

    private val updateResultLauncher: ActivityResultLauncher<IntentSenderRequest> =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                Log.e(TAG, "Update flow failed! Result code: ${result.resultCode}")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                LaundryNavGraph(navController = navController, viewModel = viewModel)
            }
        }
        checkForUpdate()
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager?.apply {
            appUpdateInfo.addOnSuccessListener { result: AppUpdateInfo? ->
                    if (result?.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                        startUpdateFlowForResult(
                            result,
                            updateResultLauncher,
                            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
                        )
                    }
            }
        }
    }

    private fun checkForUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        appUpdateManager?.registerListener(installStateUpdatedListener)

        appUpdateManager?.apply {
            appUpdateInfo.addOnSuccessListener {
                if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                        it.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        startUpdateFlowForResult(
                            it,
                            updateResultLauncher,
                            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
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

    override fun onDestroy() {
        super.onDestroy()
        appUpdateManager?.unregisterListener(installStateUpdatedListener)
        appUpdateManager = null
    }

}
