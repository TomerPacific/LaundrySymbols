package com.tomerpacific.laundry.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
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
import com.tomerpacific.laundry.viewmodel.HowToDoLaundryDrawerItems
import com.tomerpacific.laundry.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class HowToDoLaundryFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private val drawerItems = listOf<HowToDoLaundryDrawerItems>(
        HowToDoLaundryDrawerItems.SEPARATING_LAUNDRY,
        HowToDoLaundryDrawerItems.TREATING_STAINS,
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val selectedDrawerItem = viewModel.selectedDrawerItem.value
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("How To Do Laundry Steps")
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
                                Column() {
                                    drawerItems.forEach {
                                        NavigationDrawerItem(
                                            label = { Text(text = it.name) },
                                            selected = selectedDrawerItem == it,
                                            onClick = {
                                                viewModel.handleClickOnHowToDoLaundryCategories(it)
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
                        when (selectedDrawerItem) {
                            HowToDoLaundryDrawerItems.SEPARATING_LAUNDRY -> {
                                SeparatingLaundry()
                            }
                            HowToDoLaundryDrawerItems.TREATING_STAINS -> {
                                TreatingStains()
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun SeparatingLaundry() {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text("Separating Laundry", textAlign = TextAlign.Center, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.laundry_hamper),
                    contentDescription = "Separating Laundry",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
            }
        }
    }

    @Composable
    fun TreatingStains() {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text("Treating Stains", textAlign = TextAlign.Center, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.stain_removal),
                    contentDescription = "Treating Stains",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
            }
        }
    }
}