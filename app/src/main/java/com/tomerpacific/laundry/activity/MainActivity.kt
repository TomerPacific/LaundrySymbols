package com.tomerpacific.laundry.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.UPDATE_REQUEST_CODE
import com.tomerpacific.laundry.fragment.LaundryCategoriesFragmentCompose

class MainActivity : AppCompatActivity() {

    private val TAG : String = MainActivity::class.java.simpleName
    private var appUpdateManager : AppUpdateManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val laundryCategoriesFragmentCompose = LaundryCategoriesFragmentCompose()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container_view, laundryCategoriesFragmentCompose)
            .commit()
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

    override fun onBackPressed() {
        when(supportFragmentManager.backStackEntryCount) {
            0 -> super.onBackPressed()
            else -> supportFragmentManager.popBackStack()
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

