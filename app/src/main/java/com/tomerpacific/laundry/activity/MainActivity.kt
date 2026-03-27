package com.tomerpacific.laundry.activity

import android.content.IntentSender
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
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.tomerpacific.laundry.LaundryTypography
import com.tomerpacific.laundry.ui.navigation.LaundryNavGraph
import com.tomerpacific.laundry.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val TAG : String = MainActivity::class.java.simpleName
    private var appUpdateManager : AppUpdateManager? = null
    private val viewModel: MainViewModel by viewModels()

    private val installStateUpdatedListener = InstallStateUpdatedListener { state ->
        val status = state.installStatus()
        Log.d(TAG, "installStateUpdatedListener: ${getInstallStatusMessage(status)}")
    }

    private val updateResultLauncher: ActivityResultLauncher<IntentSenderRequest> =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode != RESULT_OK) {
                Log.e(TAG, "Update flow failed! Result code: ${result.resultCode}")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MaterialTheme(typography = LaundryTypography) {
                LaundryNavGraph(navController = navController, viewModel = viewModel)
            }
        }
        checkForUpdate()
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager?.appUpdateInfo?.addOnSuccessListener(this) { info ->
            if (info.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                try {
                    appUpdateManager?.startUpdateFlowForResult(
                        info,
                        updateResultLauncher,
                        AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "onResume: failed to start update flow", e)
                }
            }
        }?.addOnFailureListener(this) { e ->
            Log.e(TAG, "onResume: failed to get app update info", e)
        }
    }

    private fun checkForUpdate() {
        val manager = AppUpdateManagerFactory.create(applicationContext)
        appUpdateManager = manager
        manager.registerListener(installStateUpdatedListener)

        manager.appUpdateInfo.addOnSuccessListener(this) { info ->
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                info.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                try {
                    manager.startUpdateFlowForResult(
                        info,
                        updateResultLauncher,
                        AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "checkForUpdate: failed to start update flow", e)
                }
            }

            Log.d(TAG, "checkForUpdate: ${getInstallStatusMessage(info.installStatus())}")
        }.addOnFailureListener(this) { e ->
            Log.e(TAG, "checkForUpdate: failed to get app update info", e)
        }
    }

    private fun getInstallStatusMessage(@InstallStatus status: Int): String {
        return when (status) {
            InstallStatus.DOWNLOADED -> "update downloaded"
            InstallStatus.INSTALLED -> "update installed"
            InstallStatus.INSTALLING -> "update installing"
            InstallStatus.DOWNLOADING -> "update downloading"
            InstallStatus.CANCELED -> "update cancelled"
            InstallStatus.PENDING -> "update pending"
            InstallStatus.FAILED -> "update failed"
            InstallStatus.UNKNOWN -> "update unknown status"
            else -> "unknown status $status"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appUpdateManager?.unregisterListener(installStateUpdatedListener)
        appUpdateManager = null
    }

}
