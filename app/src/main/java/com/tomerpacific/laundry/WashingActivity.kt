package com.tomerpacific.laundry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WashingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_washing)
        Utilities.setTooltipsAndListeners(findViewById(R.id.scroll_view))
        Utilities.setFont(this, BANGERS_FONT, R.id.textView)

    }


}
