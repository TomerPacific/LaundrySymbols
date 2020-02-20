package com.tomerpacific.laundry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class IroningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ironing)
        Utilities.setFont(this, BANGERS_FONT, R.id.textView)
        Utilities.setTooltipsAndListeners(findViewById(R.id.container))
    }

}
