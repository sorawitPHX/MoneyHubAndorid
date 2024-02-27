@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.project_1

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyhubandorid.R
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
                        /* ตัวอย่างเช่น */
                    },
                    modifier = Modifier.size(48.dp) // กำหนดขนาดของปุ่ม
                ) {
                    Icon(Icons.Default.Cancel, contentDescription = "ค่าใช้จ่าย")
                }
                Spacer(modifier = Modifier.weight(1f))
                // ปุ่ม "ค่าใช้จ่าย"
                Button(
                    onClick = {
                        /* ตัวอย่างเช่น */
                    }
                ) {
                    Text("ค่าใช้จ่าย")
                }
                Spacer(modifier = Modifier.width(8.dp))
                // ปุ่ม "รายได้"
                Button(
                    onClick = {
                        /* ตัวอย่างเช่น */
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
        ExpenseIconButtonRow()
        Spacer(modifier = Modifier.height(16.dp))
        ExpenseIconButtonRow2()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ExpenseIconButton(
                iconRes = R.drawable.settings,
                label = "การตั้งค่า",
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ExpenseIconButtonRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        ExpenseIconButton(
            iconRes = R.drawable.fast_food,
            label = "อาหาร",
            onClick = { /*TODO*/ }
        )
        ExpenseIconButton(
            iconRes = R.drawable.calendar_6540110,
            label = "รายวัน",
            onClick = { /*TODO*/ }
        )
        ExpenseIconButton(
            iconRes = R.drawable.bus_school,
            label = "การจราจร",
            onClick = { /*TODO*/ }
        )
        ExpenseIconButton(
            iconRes = R.drawable.toast,
            label = "ทางสังคม",
            onClick = { /*TODO*/ }
        )
    }
}

@Composable
fun ExpenseIconButtonRow2() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        ExpenseIconButton(
            iconRes = R.drawable.house,
            label = "ที่อยู่อาศัย",
            onClick = { /*TODO*/ }
        )
        ExpenseIconButton(
            iconRes = R.drawable.gift,
            label = "ของขวัญ",
            onClick = { /*TODO*/ }
        )
        ExpenseIconButton(
            iconRes = R.drawable.chat,
            label = "สื่อสาร",
            onClick = { /*TODO*/ }
        )
        ExpenseIconButton(
            iconRes = R.drawable.clothes_rack,
            label = "เสื้อผ้า",
            onClick = { /*TODO*/ }
        )
    }

}

@Composable
fun ExpenseIconButton(
    iconRes: Int,
    label: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(100.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(120.dp)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = label,
                    fontSize = 15.sp,
                    color = Color.Black,
                )
            }
        }
    }
}
