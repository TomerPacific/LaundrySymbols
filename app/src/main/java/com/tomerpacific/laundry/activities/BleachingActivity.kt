package com.tomerpacific.laundry.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tomerpacific.laundry.BANGERS_FONT
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.Utilities

class BleachingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bleaching)
        Utilities.setFont(
            this,
            BANGERS_FONT,
            R.id.textView
        )
        Utilities.setTooltipsAndListeners(findViewById(R.id.container))
    }

}
