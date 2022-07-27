package com.tomerpacific.laundry.fragment

import Tooltip
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.LAUNDRY_CATEGORY_KEY
import com.tomerpacific.laundry.viewmodel.MainViewModel

class LaundryCategoryFragmentCompose: Fragment() {

    private val model: MainViewModel by activityViewModels()

    companion object {
        fun newInstance(laundryCategory: String) : LaundryCategoryFragmentCompose {
            val fragment = LaundryCategoryFragmentCompose()
            val args = Bundle()
            args.putString(LAUNDRY_CATEGORY_KEY, laundryCategory)
            fragment.arguments = args
            return fragment
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val laundryCategoryName: String = arguments?.getString(LAUNDRY_CATEGORY_KEY) ?: ""

        return ComposeView(requireContext()).apply {
            setContent {
                Column {
                    Text(
                        laundryCategoryName,
                        Modifier.align(Alignment.CenterHorizontally),
                        fontFamily = Bangers,
                        fontSize = 30.sp
                    )
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3), modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(model.getItemsForLaundryCategory(laundryCategoryName)) { laundrySymbol ->
                            Box {
                                val showTooltip = remember { mutableStateOf(false) }

                                Box(
                                    modifier = Modifier
                                        .combinedClickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(),
                                            onClickLabel = "Laundry Symbol Name",
                                            role = Role.Button,
                                            onClick = {
                                                val fragment: LaundrySymbolFragmentCompose =
                                                LaundrySymbolFragmentCompose.newInstance(laundrySymbol)
                                                model.handleClickOnLaundrySymbol(requireActivity(), fragment)
                                            },
                                            onLongClick = { showTooltip.value = true },
                                        ),
                                ) {
                                    Image(painterResource
                                        (laundrySymbol.drawableId),
                                        laundrySymbol.description,
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(100.dp)
                                            .border(BorderStroke(2.dp, Color.Black))
                                    )
                                }

                                Tooltip(showTooltip) {
                                    Text(laundrySymbol.name)
                                }
                            }
                        }
                    }

                }
            }
        }
    }


}