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

        val onClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    openActivity(v)
                }
            }
        }

        Utilities.setListenerForView(
            findViewById(R.id.custom_button_washing),
            onClickListener
        )
        Utilities.setListenerForView(
            findViewById(R.id.custom_button_bleaching),
            onClickListener
        )
        Utilities.setListenerForView(
            findViewById(R.id.custom_button_drying),
            onClickListener
        )
        Utilities.setListenerForView(
            findViewById(R.id.custom_button_ironing),
            onClickListener
        )

    }

    private fun openActivity(view: View) {

        val activityToOpen : Intent? = when(view.tag) {
            WASHING -> Intent(this, WashingActivity::class.java)
            BLEACHING -> Intent(this, BleachingActivity::class.java)
            DRYING -> Intent(this, DryingActivity::class.java)
            IRONING -> Intent(this, IroningActivity::class.java)
            else -> null
        }

        if (activityToOpen != null) {
            startActivity(activityToOpen)
        }
    }


    private fun checkForUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)

        val appUpdateInfoTask = appUpdateManager?.appUpdateInfo

        appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                try {
                    appUpdateManager?.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        UPDATE_REQUEST_CODE
                    )
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
                //Already downloaded update, do nothing
                Log.d(TAG, "checkForUpdate update downloaded")
            } else  if (appUpdateInfo.installStatus() == InstallStatus.FAILED ||
                        appUpdateInfo.installStatus() == InstallStatus.UNKNOWN) {
                Log.e(TAG, "checkForUpdate checkForUpdate updateAvailability FAILED")
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

