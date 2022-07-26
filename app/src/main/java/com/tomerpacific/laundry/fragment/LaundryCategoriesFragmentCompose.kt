package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.BuildConfig
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.viewmodel.MainViewModel

class LaundryCategoriesFragmentCompose : Fragment() {

    private val model: MainViewModel by activityViewModels()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(verticalArrangement = Arrangement.spacedBy(100.dp)) {
                    Row(Modifier.align(CenterHorizontally)) {
                        Text(
                            stringResource(
                                id = R.string.main_screen_title
                            ),
                            fontFamily = Bangers,
                            fontSize = 30.sp
                        )
                    }
                    Row(Modifier.align(CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                        ) {
                            Image(
                                painterResource(id = R.drawable.washable),
                                stringResource(id = R.string.washing_symbol),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .border(BorderStroke(2.dp, Color.Black))
                                    .clickable(enabled = true, onClick = {
                                        openLaundryCategory(resources.getString(R.string.washing))
                                    }),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = stringResource(id = R.string.washing),
                                Modifier
                                    .padding(2.dp)
                                    .align(CenterHorizontally),
                                fontSize = 16.sp
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Top,
                        ) {
                            Image(
                                painterResource(id = R.drawable.bleach_allow),
                                stringResource(id = R.string.bleaching_symbol),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .border(BorderStroke(2.dp, Color.Black))
                                    .clickable(enabled = true, onClick = {
                                        openLaundryCategory(resources.getString(R.string.bleaching))
                                    }),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = stringResource(id = R.string.bleaching),
                                Modifier
                                    .padding(2.dp)
                                    .align(CenterHorizontally),
                                fontSize = 16.sp
                            )
                        }
                    }
                    Row(Modifier.align(CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                        ) {
                            Image(
                                painterResource(id = R.drawable.dry_cleaning_allow),
                                stringResource(id = R.string.drying_symbol),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .border(BorderStroke(2.dp, Color.Black))
                                    .clickable(enabled = true, onClick = {
                                        openLaundryCategory(resources.getString(R.string.drying))
                                    }),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = stringResource(id = R.string.drying),
                                Modifier
                                    .padding(2.dp)
                                    .align(CenterHorizontally),
                                fontSize = 16.sp
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Top,
                        ) {
                            Image(
                                painterResource(id = R.drawable.iron_allowed),
                                stringResource(id = R.string.ironing_symbol),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .border(BorderStroke(2.dp, Color.Black))
                                    .clickable(enabled = true, onClick = {
                                        openLaundryCategory(resources.getString(R.string.ironing))
                                    }),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = stringResource(id = R.string.ironing),
                                Modifier
                                    .padding(2.dp)
                                    .align(CenterHorizontally),
                                fontSize = 16.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(Modifier.align(Alignment.End)) {
                        Text(
                            text = getString(R.string.app_version, BuildConfig.VERSION_NAME),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }

    private fun openLaundryCategory(laundryCategory:String) {

        val fragment: LaundryCategoryFragmentCompose =
            LaundryCategoryFragmentCompose.newInstance(laundryCategory)

        model.handleClickOnLaundryCategory(requireActivity(), fragment)
    }

}