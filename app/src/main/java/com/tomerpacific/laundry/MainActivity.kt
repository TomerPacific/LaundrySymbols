package com.tomerpacific.laundry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.tomerpacific.laundry.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        val washingDrawable = resources.getDrawable(R.drawable.washable)
        val bleachingDrawable = resources.getDrawable(R.drawable.bleach_allow)
        val dryingDrawable = resources.getDrawable(R.drawable.dry_cleaning_allow)
        val ironingDrawable = resources.getDrawable(R.drawable.iron_allowed)

        val washingSymbol : Symbol = Symbol(WASHING, washingDrawable)
        val bleachingSymbol : Symbol = Symbol(BLEACHING, bleachingDrawable)
        val dryingSymbol : Symbol = Symbol(DRYING, dryingDrawable)
        val ironingSymbol : Symbol = Symbol(IRONING, ironingDrawable)

        val customButtons : List<Symbol> = listOf(washingSymbol, bleachingSymbol,dryingSymbol, ironingSymbol)
        binding.symbols = SymbolData(customButtons)

        Utilities.setFont(this, BANGERS_FONT, R.id.textView)

        val versionTextView : TextView = findViewById(R.id.app_version)
        versionTextView.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)
        setListenersForButtons()


    }

    private fun setListenersForButtons() {

        val onClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    openActivity(v)
                }
            }
        }

        Utilities.setListenerForView(findViewById(R.id.custom_button_washing), onClickListener)
        Utilities.setListenerForView(findViewById(R.id.custom_button_bleaching), onClickListener)
        Utilities.setListenerForView(findViewById(R.id.custom_button_drying), onClickListener)
        Utilities.setListenerForView(findViewById(R.id.custom_button_ironing), onClickListener)

    }

    private fun openActivity(view: View) {

        val activityToOpen : Intent? = when(view.tag) {
            WASHING-> Intent(this, WashingActivity::class.java)
            BLEACHING-> Intent(this, BleachingActivity::class.java)
            DRYING-> Intent(this, DryingActivity::class.java)
            IRONING-> Intent(this, IroningActivity::class.java)
            else -> null
        }

        if (activityToOpen != null) {
            startActivity(activityToOpen)
        }
    }

}

