package com.example.moneyhubandorid.screen.Finance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun income(navController: NavHostController){
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        // verticalArrangement = Arrangement.Center
    ) {
        CenterAlignedTopAppBar(

            title = {
                /* ตัวอย่างเช่น */
            },
            actions = {
                // Notifications icon
                IconButton(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        if (navController.currentBackStack.value.size >= 2) {
                            navController.popBackStack()
                        }
                        navController.navigate(Screen.Login.route)
                    },
                    modifier = Modifier.size(48.dp) // กำหนดขนาดของปุ่ม
                ) {
                    Icon(Icons.Default.Cancel, contentDescription = "ค่าใช้จ่าย")
                }
                Spacer(modifier = Modifier.weight(1f))
                // ปุ่ม "ค่าใช้จ่าย"
                Button(
                    onClick = {
                        coroutineScope.launch {

                        }
                    }
                ) {
                    Text("ค่าใช้จ่าย")
                }
                Spacer(modifier = Modifier.width(8.dp))
                // ปุ่ม "รายได้"
                Button(
                    onClick = {
                        /*TODO: ดำเนินการเมื่อคลิกปุ่มรายได้*/
                    }
                ) {
                    Text("รายได้")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        /* ตัวอย่างเช่น */
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(Icons.Default.Check, contentDescription = "รายได้")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Blue.copy(alpha = 0.3f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        IncomeIconButtonRow() //ของปุ่มรายได้
    }
}
