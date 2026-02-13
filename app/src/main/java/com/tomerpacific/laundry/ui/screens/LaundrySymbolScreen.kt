package com.tomerpacific.laundry.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomerpacific.laundry.model.TemperatureUnit
import com.tomerpacific.laundry.viewmodel.MainViewModel

@Composable
fun LaundrySymbolScreen(viewModel: MainViewModel, symbolName: String?) {

    val laundrySymbol = viewModel.findSymbolByName(symbolName.orEmpty())

    laundrySymbol?.let {
        val temperatureUnit by viewModel.temperatureUnit

        val name = when (temperatureUnit) {
            TemperatureUnit.CELSIUS -> laundrySymbol.name
            TemperatureUnit.FAHRENHEIT -> laundrySymbol.nameFahrenheit ?: laundrySymbol.name
        }

        val description = when (temperatureUnit) {
            TemperatureUnit.CELSIUS -> laundrySymbol.description
            TemperatureUnit.FAHRENHEIT -> laundrySymbol.nameFahrenheit ?: laundrySymbol.description
        }

        val drawableId = when (temperatureUnit) {
            TemperatureUnit.CELSIUS -> laundrySymbol.drawableId
            TemperatureUnit.FAHRENHEIT -> laundrySymbol.drawableIdFahrenheit ?: laundrySymbol.drawableId
        }

        Scaffold(
            contentWindowInsets = WindowInsets.safeContent
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    name,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = name,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
                Text(
                    description,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
    } ?: run {
        Text("Symbol not found")
    }
}