package com.tomerpacific.laundry.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LaundrySymbolScreen(symbolName: String, @DrawableRes symbolDrawableId: Int) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                symbolName,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = symbolDrawableId),
                contentDescription = symbolName,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
            Text(
                symbolName,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}