package com.tomerpacific.laundry.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.model.LaundrySymbol

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LaundryCategoryScreen(
    laundryCategory: String,
    laundrySymbols: List<LaundrySymbol>,
    onSymbolClick: (LaundrySymbol) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                laundryCategory,
                Modifier.align(Alignment.CenterHorizontally).padding(top = 30.dp),
                fontFamily = Bangers,
                fontSize = 30.sp
            )
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
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        tooltip = { 
                            Surface(
                                modifier = Modifier.padding(4.dp),
                                shape = RoundedCornerShape(4.dp),
                                color = Color.Gray,
                                border = BorderStroke(1.dp, Color.Black)
                            ) {
                                Text(
                                    text = laundrySymbol.description,
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.White
                                )
                            }
                        },
                        state = rememberTooltipState()
                    ) {
                        Image(
                            painter = painterResource(laundrySymbol.drawableId),
                            contentDescription = laundrySymbol.description,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .border(BorderStroke(2.dp, Color.Black))
                                .clickable(
                                    enabled = true,
                                    onClick = { onSymbolClick(laundrySymbol) },
                                    role = Role.Button
                                )
                                .testTag(laundrySymbol.name)
                        )
                    }
                }
            }
        }
    }
}