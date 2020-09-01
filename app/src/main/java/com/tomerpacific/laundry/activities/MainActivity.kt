package com.tomerpacific.laundry.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.tomerpacific.laundry.*
import com.tomerpacific.laundry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG : String = MainActivity::class.java.simpleName
    private var appUpdateManager : AppUpdateManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        Utilities.setFont(
            this,
            BANGERS_FONT,
            R.id.textView
        )
        val versionTextView : TextView = findViewById(R.id.app_version)
        versionTextView.text = getString(
            R.string.app_version,
            BuildConfig.VERSION_NAME
        )
        setListenersForButtons()
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

    private fun setupDataBinding() {

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        val washingDrawable = resources.getDrawable(R.drawable.washable)
        val bleachingDrawable = resources.getDrawable(R.drawable.bleach_allow)
        val dryingDrawable = resources.getDrawable(R.drawable.dry_cleaning_allow)
        val ironingDrawable = resources.getDrawable(R.drawable.iron_allowed)

        val washingSymbol : Symbol =
            Symbol(WASHING, washingDrawable)
        val bleachingSymbol : Symbol =
            Symbol(BLEACHING, bleachingDrawable)
        val dryingSymbol : Symbol =
            Symbol(DRYING, dryingDrawable)
        val ironingSymbol : Symbol =
            Symbol(IRONING, ironingDrawable)

        val customButtons : List<Symbol> = listOf(washingSymbol, bleachingSymbol,dryingSymbol, ironingSymbol)
        binding.symbols = SymbolData(customButtons)
    }

    private fun setListenersForButtons() {

        val onClickListener = View.OnClickListener {
                    openActivity(it)
        }

        val customButtons : List<Int> = listOf(R.id.custom_button_washing,
            R.id.custom_button_bleaching,
            R.id.custom_button_drying,
            R.id.custom_button_ironing)

        customButtons.forEach {
            Utilities.setListenerForView(findViewById(it), onClickListener)
        }
    }

    private fun openActivity(view: View) {

        val activityToOpen : Intent? = when(view.tag) {
            WASHING -> Intent(this, WashingActivity::class.java)
            BLEACHING -> Intent(this, BleachingActivity::class.java)
            DRYING -> Intent(this, DryingActivity::class.java)
            IRONING -> Intent(this, IroningActivity::class.java)
            else -> null
        }

        activityToOpen?.apply {
            startActivity(activityToOpen)
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

