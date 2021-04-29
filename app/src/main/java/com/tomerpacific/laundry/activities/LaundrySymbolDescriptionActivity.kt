package com.tomerpacific.laundry.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tomerpacific.laundry.*

class LaundrySymbolDescriptionActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.symbol_description)
        setSymbolDataInUI()
    }

    private fun setSymbolDataInUI() {

        findViewById<TextView>(R.id.symbol_header).apply {
            text = intent.getStringExtra(SYMBOL_NAME_KEY)
        }

        findViewById<TextView>(R.id.symbol_description).apply {
            text = intent.getStringExtra(SYMBOL_DESCRIPTION_KEY)
        }

        intent.getStringExtra(SYMBOL_IMAGE_KEY)?.also {
            findViewById<ImageView>(R.id.symbol_image).apply {
                val imageName = Utilities.extractImagePath(it)
                val imageId = applicationContext.resources.getIdentifier(
                    imageName,
                    "drawable",
                    applicationContext.packageName
                )

                setImageResource(imageId)
            }
        }
    }

}