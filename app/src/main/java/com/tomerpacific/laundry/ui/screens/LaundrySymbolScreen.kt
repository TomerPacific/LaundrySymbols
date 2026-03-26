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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaundrySymbolScreen(viewModel: MainViewModel, symbolId: String?, onBackClick: () -> Unit) {

    val laundrySymbol = viewModel.findSymbolById(symbolId.orEmpty())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = laundrySymbol?.name ?: stringResource(id = R.string.symbol_not_found),
                        fontFamily = Bangers,
                        fontSize = 30.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button_content_description)
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        laundrySymbol?.let {
            val temperatureUnit by viewModel.temperatureUnit

            val name = laundrySymbol.name

            val description = laundrySymbol.descriptionFor(temperatureUnit)
            val drawableId = laundrySymbol.drawableIdFor(temperatureUnit)

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    name,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.semantics { heading() }
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
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .testTag("symbol_description_text")
                )
            }
        } ?: run {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.symbol_not_found),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.semantics { heading() }
                )
            }
        }
    }
}
