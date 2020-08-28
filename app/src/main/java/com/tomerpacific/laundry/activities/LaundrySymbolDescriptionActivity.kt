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

        val symbolName : String? = intent.getStringExtra(SYMBOL_NAME_KEY)
        val symbolImageSrc : String? = intent.getStringExtra(SYMBOL_IMAGE_KEY)
        val symbolDescriptionText : String? = intent.getStringExtra(SYMBOL_DESCRIPTION_KEY)

        val symbolHeader : TextView = findViewById(R.id.symbol_header)
        val symbolImage : ImageView = findViewById(R.id.symbol_image)
        val symbolDescription : TextView = findViewById(R.id.symbol_description)

        symbolHeader.text = symbolName

        if (symbolImageSrc != null) {
            val imagePath =
                Utilities.extractImagePath(symbolImageSrc)
            val imageId = applicationContext.resources.getIdentifier(imagePath,
                null,
                applicationContext.packageName)

            symbolImage.setImageResource(imageId)
        }

        symbolDescription.text = symbolDescriptionText

    }

}