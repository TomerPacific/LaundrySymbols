package com.tomerpacific.laundry.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                symbolName,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(id = symbolDrawableId),
                contentDescription = symbolName,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                symbolName,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}