package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tomerpacific.laundry.R
import com.tomerpacific.laundry.StyledText
import com.tomerpacific.laundry.model.HowToDoLaundryCategory
import com.tomerpacific.laundry.textResource
import com.tomerpacific.laundry.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class HowToDoLaundryFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val howToDoLaundryCategories = viewModel.getHowToDoLaundryCategories()

        return ComposeView(requireContext()).apply {
            setContent {

                val selectedDrawerItem = viewModel.selectedDrawerItem.value
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                Scaffold(
                    contentWindowInsets = WindowInsets.safeContent,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(resources.getString(R.string.how_to_do_laundry_title))
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            },
                        )
                    },
                ) { innerPadding ->
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        modifier = Modifier
                            .padding(innerPadding),
                        drawerContent = {
                            ModalDrawerSheet {
                                LazyColumn(
                                    contentPadding = innerPadding
                                ) {
                                    items(howToDoLaundryCategories) { howToDoLaundryCategory ->
                                        NavigationDrawerItem(
                                            label = { Text(text = resources.getString(howToDoLaundryCategory.name)) },
                                            selected = selectedDrawerItem == howToDoLaundryCategory,
                                            onClick = {
                                                viewModel.handleClickOnHowToDoLaundryCategories(howToDoLaundryCategory)
                                                scope.launch {
                                                    drawerState.close()
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    ) {
                        howToDoLaundryCategories.find { howToDoLaundryCategory ->
                            howToDoLaundryCategory == selectedDrawerItem
                        }?.let {
                            HowToDoLaundryCategory(it)
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun HowToDoLaundryCategory(howToDoLaundryCategory: HowToDoLaundryCategory) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        resources.getString(howToDoLaundryCategory.name),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            item {
                Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.Center) {
                    Image(
                        painter = painterResource(id = howToDoLaundryCategory.drawableId),
                        contentDescription = getString(howToDoLaundryCategory.contentDescriptionId),
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    )
                }
            }
            item {
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp),
                    horizontalArrangement = Arrangement.Center) {
                    StyledText(textResource(howToDoLaundryCategory.descriptionId))
                }
            }
        }
    }
}
