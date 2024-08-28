package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch

class HowToDoLaundryFragment: Fragment() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("How To Do Laundry")
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
                        modifier = Modifier.padding(innerPadding),
                        drawerContent = {
                            ModalDrawerSheet {
                                NavigationDrawerItem(
                                    label = { Text(text = "Sorting Laundry") },
                                    selected = false,
                                    onClick = {

                                    }
                                )
                            }
                        }
                    ) {

                    }
                }
            }
        }
    }
}