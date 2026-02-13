package com.tomerpacific.laundry.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.model.LaundrySymbol
import com.tomerpacific.laundry.model.TemperatureUnit
import com.tomerpacific.laundry.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaundryCategoryScreen(
    @StringRes laundryCategory: Int,
    viewModel: MainViewModel,
    onSymbolClick: (LaundrySymbol) -> Unit
) {

    val laundrySymbols = viewModel.getItemsForLaundryCategory(laundryCategory)
    val temperatureUnit by viewModel.temperatureUnit
    val containsSymbolWithTemperature = laundrySymbols.any { it.temperature != null }

    Scaffold(
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                stringResource(id = laundryCategory),
                Modifier.fillMaxWidth().padding(top = 30.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontFamily = Bangers,
                fontSize = 30.sp
            )
            if (containsSymbolWithTemperature) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(end = 30.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🌡️")
                    val currentUnit = if (temperatureUnit == TemperatureUnit.FAHRENHEIT) "Fahrenheit" else "Celsius"
                    val contentDescription = stringResource(id = R.string.temperature_unit_toggle, currentUnit)
                    Switch(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .semantics { this.contentDescription = contentDescription },
                        checked = temperatureUnit == TemperatureUnit.FAHRENHEIT,
                        onCheckedChange = {
                            viewModel.toggleTemperatureUnit()
                        }
                    )
                    val toggleText = when (temperatureUnit) {
                        TemperatureUnit.CELSIUS -> "°C"
                        TemperatureUnit.FAHRENHEIT -> "°F"
                    }
                    Text(text = toggleText)
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    Alignment.CenterVertically
                ),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(laundrySymbols) { laundrySymbol ->
                    val description = when (temperatureUnit) {
                        TemperatureUnit.CELSIUS -> laundrySymbol.description
                        TemperatureUnit.FAHRENHEIT -> laundrySymbol.descriptionFahrenheit ?: laundrySymbol.description
                    }
                    val name = when (temperatureUnit) {
                        TemperatureUnit.CELSIUS -> laundrySymbol.name
                        TemperatureUnit.FAHRENHEIT -> laundrySymbol.descriptionFahrenheit ?: laundrySymbol.name
                    }
                    val drawableId = when (temperatureUnit) {
                        TemperatureUnit.CELSIUS -> laundrySymbol.drawableId
                        TemperatureUnit.FAHRENHEIT -> laundrySymbol.drawableIdFahrenheit ?: laundrySymbol.drawableId
                    }

                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
                        tooltip = {
                            Surface(
                                modifier = Modifier.padding(4.dp),
                                shape = RoundedCornerShape(4.dp),
                                color = Color.Gray,
                                border = BorderStroke(1.dp, Color.Black)
                            ) {
                                Text(
                                    text = description,
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.White
                                )
                            }
                        },
                        state = rememberTooltipState()
                    ) {
                        Image(
                            painter = painterResource(drawableId),
                            contentDescription = description,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .border(BorderStroke(2.dp, Color.Black))
                                .clickable(
                                    enabled = true,
                                    onClick = { onSymbolClick(laundrySymbol) },
                                    role = Role.Button
                                )
                                .testTag(name)
                        )
                    }
                }
            }
        }
    }
}