package com.tomerpacific.laundry.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.BuildConfig
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.model.LaundryCategory

@Composable
fun LaundryCategoriesScreen(
    categories: List<LaundryCategory>,
    onCategoryClick: (Int) -> Unit,
    onLearnMoreClick: () -> Unit,
    onVersionClick: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
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
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .widthIn(max = 240.dp)
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categories) { category ->
                    CategoryTile(category, onCategoryClick)
                }
            }

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
                        contentDescription = stringResource(R.string.laundry_machine_content_description),
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

@Composable
fun CategoryTile(category: LaundryCategory, onCategoryClick: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = category.drawableId),
            stringResource(id = category.contentDescriptionId),
            modifier = Modifier
                .size(100.dp)
                .border(BorderStroke(2.dp, Color.Black))
                .clickable(enabled = true, onClick = {
                    onCategoryClick(category.name)
                })
                .testTag(category.testTag),
            alignment = Alignment.Center
        )
        Text(
            text = stringResource(id = category.name),
            Modifier.padding(2.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}
