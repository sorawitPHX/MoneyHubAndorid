package com.example.moneyhubandorid.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moneyhubandorid.NavGraph
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.SharePreferencesManager

@Composable
fun HomeScreen(navController: NavHostController) {
    val contextForToast = LocalContext.current.applicationContext
//    val navController = rememberNavController()
    Scaffold(
        topBar = {MyTopAppBar(navController,contextForToast = contextForToast)},
        bottomBar = { MyBottomBar(navController, contextForToast) },
        floatingActionButtonPosition = FabPosition.End
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Screen area")
        }
//        NavGraph(navController)
    }

}
@Composable
fun MyBottomBar(navController: NavController, contextForToast: Context){
    val navigationItems = listOf(
        Screen.Home,
        Screen.Finance,
        Screen.Analysis,
        Screen.Profile,
    )
    var selectedScreen by remember {
        mutableStateOf(0) // or use mutableStateOf(0)
    }
    NavigationBar {
        navigationItems.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(text = screen.name) },
                selected = (selectedScreen == index),
                onClick = { //this if condition keeps only one screen in the back stack
                    if (navController.currentBackStack.value.size >= 2) {
                        navController.popBackStack()
                    }
                    selectedScreen = index
                    navController.navigate(screen.route)
                    Toast.makeText(contextForToast, screen.name, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavHostController, contextForToast: Context) {
    lateinit var sharePreferences: SharePreferencesManager
    sharePreferences = SharePreferencesManager(contextForToast)
    val userId = sharePreferences.userId ?: ""
    var rememberVal by remember {
        mutableStateOf(false)
    }
    var loggotDialog by remember{mutableStateOf(false)}
    var expanded by remember {
        mutableStateOf(false)
    }
    CenterAlignedTopAppBar(
        title = {
            Text(text = "My Application")
        },
        actions = {
            // Notifications Icon
            IconButton(
                onClick = {
                    Toast.makeText(contextForToast, "Notifications", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(imageVector = Icons.Outlined.NotificationsNone, contentDescription = "Notifications")
            }

            // Home Icon
            IconButton(
                onClick = {
                    Toast.makeText(contextForToast, "Home", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home")
            }

            // Vertical 3 dots icon
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Open Menu")
            }

            //Dropdown Menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ) {
                // Menu Items
                // Setting Icons
                DropdownMenuItem(
                    text = {
                        Text(text = "Settings") },
                    onClick = {
                        Toast.makeText(contextForToast, "Settings", Toast.LENGTH_SHORT).show()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Settings, contentDescription = null)
                    }
                )

                // Logout Icon
                DropdownMenuItem(
                    text = {
                        Text(text = "Logout") },
                    onClick = {
                        Toast.makeText(contextForToast, "Logout", Toast.LENGTH_SHORT).show()
                        expanded = false
                        loggotDialog = true
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Logout , contentDescription = null)
                    }
                )

                if(loggotDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            loggotDialog = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    loggotDialog = false
                                    if(rememberVal) {
                                        sharePreferences.clearUserLogin()
                                    }else {
                                        sharePreferences.clearUserAll()
                                    }
                                    if (navController.currentBackStack.value.size >= 2) {
                                        navController.popBackStack()
                                    }
                                    navController.navigate(Screen.Login.route)
                                }
                            ){
                                Text(text = "Yes")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                loggotDialog = false
                            }) {
                                Text(text = "No")
                            }
                        },
                        title = {Text(text = "Logout")},
                        text = {
                            Column {
                                Text(text = "Do you want to logout?")
                                Row (
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Checkbox(checked = rememberVal, onCheckedChange = { rememberVal = it })
                                    Text(text = "Remember your student ID")
                                }
                            }
                        }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Green.copy(alpha = 0.3f)
        )
    )
}

