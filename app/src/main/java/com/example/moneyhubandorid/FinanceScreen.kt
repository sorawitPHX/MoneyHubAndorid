@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.project_1

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moneyhubandorid.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen() {
    val contextForToast = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val navigationItems = listOf(
        Screen.Home,
        Screen.Finance,
        Screen.Analysis,
        Screen.Profile,
    )
    var selectedScreen by remember {
        mutableStateOf(0) // or use mutableStateOf(0)
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ){
        CenterAlignedTopAppBar(
            title = {
                 },
            actions = {
                // Notificotions icon
                Row (

                ) {
                    IconButton(
                        onClick = {
                            Toast.makeText(contextForToast, "ยกเลิกการแก้ไข", Toast.LENGTH_SHORT)
                                .show()
                        },
                        modifier = Modifier.size(48.dp) // กำหนดขนาดของปุ่ม
                    ) {
                        Icon(Icons.Default.Cancel, contentDescription = "ค่าใช้จ่าย")
                    }
                    Button(onClick = {
                        Toast.makeText(contextForToast,"ค่าใช้จ่าย", Toast.LENGTH_SHORT)
                            .show()
                    }) {
                        Text("ค่าใช้จ่าย")
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // ตัวเว้นวรรค
                    Button(onClick = {
                        Toast.makeText(contextForToast,"รายได้", Toast.LENGTH_SHORT)
                            .show()
                    }) {
                        Text("รายได้")
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(contextForToast, "บันทึกข้อมูล", Toast.LENGTH_SHORT)
                                .show()
                        },
                        modifier = Modifier.size(48.dp) // กำหนดขนาดของปุ่ม
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "รายได้")
                    }
                }

            },//end action
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Blue.copy(alpha = 0.3f)
            )
        )

//        NavigationBar {
//            navigationItems.forEachIndexed { index, screen ->
//                NavigationBarItem(
//                    icon = { Icon(imageVector = screen.icon, contentDescription = null) },
//                    label = { Text(text = screen.name) },
//                    selected = (selectedScreen == index),
//                    onClick = { //this if condition keeps only one screen in the back stack
//                        if (navController.currentBackStack.value.size >= 2) {
//                            navController.popBackStack()
//                        }
//                        selectedScreen = index
//                        navController.navigate(screen.route)
//                        Toast.makeText(contextForToast, screen.name, Toast.LENGTH_SHORT).show()
//                    }
//                )
//            }
//        }



    }
}