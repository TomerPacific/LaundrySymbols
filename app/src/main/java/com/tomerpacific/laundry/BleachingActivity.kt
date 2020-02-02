package com.tomerpacific.laundry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BleachingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bleaching)
        Utilities.setFont(this, BANGERS_FONT, R.id.textView)
        Utilities.setTooltipsAndListeners(findViewById(R.id.scroll_view))
    }

}
