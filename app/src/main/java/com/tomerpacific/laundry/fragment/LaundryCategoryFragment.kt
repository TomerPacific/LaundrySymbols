package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.Bangers
import com.tomerpacific.laundry.LAUNDRY_CATEGORY_KEY
import com.tomerpacific.laundry.viewmodel.MainViewModel


class LaundryCategoryFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    companion object {
        fun newInstance(laundryCategory: String): LaundryCategoryFragment {
            val fragment = LaundryCategoryFragment()
            val args = Bundle()
            args.putString(LAUNDRY_CATEGORY_KEY, laundryCategory)
            fragment.arguments = args
            return fragment
        }
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
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
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        items(
                            viewModel.getItemsForLaundryCategory(
                                laundryCategoryName,
                                context
                            )
                        ) { laundrySymbol ->
                            Box {
                                Box(
                                    modifier = Modifier
                                        .combinedClickable(
                                            onClickLabel = "Laundry Symbol Name",
                                            role = Role.Button,
                                            onClick = {
                                                val fragment: LaundrySymbolFragment =
                                                    LaundrySymbolFragment.newInstance(laundrySymbol)
                                                viewModel.handleClickOnLaundrySymbol(
                                                    requireActivity(),
                                                    fragment
                                                )
                                            },
                                        )
                                        .testTag(laundrySymbol.name),
                                ) {

                                    val tooltipPosition =
                                        TooltipDefaults.rememberPlainTooltipPositionProvider()
                                    val tooltipState =
                                        rememberBasicTooltipState(isPersistent = false)

                                    BasicTooltipBox(
                                        positionProvider = tooltipPosition,
                                        state = tooltipState,
                                        tooltip = {
                                            Text(
                                                text = laundrySymbol.description,
                                                color = Color.White,
                                                modifier = Modifier.padding(5.dp).background(Color.Black),
                                            )
                                        },
                                        content = {
                                            Image(
                                                painterResource
                                                    (laundrySymbol.drawableId),
                                                laundrySymbol.description,
                                                modifier = Modifier
                                                    .width(100.dp)
                                                    .height(100.dp)
                                                    .border(BorderStroke(2.dp, Color.Black))
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}