package com.tomerpacific.laundry.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.BuildConfig
import com.tomerpacific.laundry.R

@Composable
fun LaundryCategoriesScreen(
    onCategoryClick: (String) -> Unit,
    onLearnMoreClick: () -> Unit,
    onVersionClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    Scaffold(
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row(Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp)) {
                Text(
                    stringResource(
                        id = R.string.main_screen_title
                    ),
                    fontFamily = Bangers,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column {
                    Image(
                        painterResource(id = R.drawable.washable),
                        stringResource(id = R.string.washing_symbol),
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .border(BorderStroke(2.dp, Color.Black))
                            .clickable(enabled = true, onClick = {
                                onCategoryClick(context.resources.getString(R.string.washing))
                            })
                            .testTag("washing category"),
                        alignment = Alignment.Center
                    )
                    Text(
                        text = stringResource(id = R.string.washing),
                        Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                }
                Column {
                    Image(
                        painterResource(id = R.drawable.bleach_allow),
                        stringResource(id = R.string.bleaching_symbol),
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .border(BorderStroke(2.dp, Color.Black))
                            .clickable(enabled = true, onClick = {
                                onCategoryClick(context.resources.getString(R.string.bleaching))
                            })
                            .testTag("bleaching category"),
                        alignment = Alignment.Center
                    )
                    Text(
                        text = stringResource(id = R.string.bleaching),
                        Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                }
            }
            Row(
                Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column {
                    Image(
                        painterResource(id = R.drawable.dry_cleaning_allow),
                        stringResource(id = R.string.drying_symbol),
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .border(BorderStroke(2.dp, Color.Black))
                            .clickable(enabled = true, onClick = {
                                onCategoryClick(context.resources.getString(R.string.drying))
                            })
                            .testTag("drying category"),
                        alignment = Alignment.Center
                    )
                    Text(
                        text = stringResource(id = R.string.drying),
                        Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                }
                Column {
                    Image(
                        painterResource(id = R.drawable.iron_allowed),
                        stringResource(id = R.string.ironing_symbol),
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .border(BorderStroke(2.dp, Color.Black))
                            .clickable(enabled = true, onClick = {
                                onCategoryClick(context.resources.getString(R.string.ironing))
                            })
                            .testTag("ironing category"),
                        alignment = Alignment.Center
                    )
                    Text(
                        text = stringResource(id = R.string.ironing),
                        Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Max),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    modifier = Modifier.fillMaxHeight(),
                    onClick = {
                        onLearnMoreClick()
                    }) {
                    Text(
                        text = stringResource(R.string.how_to_do_laundry_button_text),
                        fontSize = 16.sp
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_local_laundry_service_24),
                        contentDescription = "Laundry Machine",
                    )
                }
                TextButton(onClick = {
                    onVersionClick()
                }) {
                    Text(
                        modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp),
                        text = stringResource(R.string.app_version, BuildConfig.VERSION_NAME),
                        fontSize = 16.sp
                    )
                }

            }
        }
    }
}