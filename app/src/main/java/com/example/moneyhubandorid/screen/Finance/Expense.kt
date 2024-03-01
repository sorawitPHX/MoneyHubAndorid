package com.example.moneyhubandorid.screen.Finance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.AppBar.FinanceTopAppBar
import com.example.moneyhubandorid.R
import com.example.moneyhubandorid.Screen

data class Category(
    var icon: Int,
    var label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expense(navController: NavHostController) {
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
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    var categoryItemList = remember { mutableStateListOf<Category>(
        Category(R.drawable.fast_food,"อาหาร"),
        Category(R.drawable.calendar_6540110,"รายวัน"),
        Category(R.drawable.bus_school,"การจราจร"),
        Category(R.drawable.toast,"ทางสังคม"),
        Category(R.drawable.house,"ที่อยู่อาศัย"),
        Category(R.drawable.gift,"ของขวัญ"),
        Category(R.drawable.chat,"สื่อสาร"),
        Category(R.drawable.clothes_rack,"เสื้อผ้า"),
//        Category(R.drawable.fast_food,"การตั้งค่า"),
    ) }

    var categorySelected by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            FinanceTopAppBar(navController, contextForToast)
        },
        bottomBar = {
            BottomBar(navController, contextForToast)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Box(
            modifier =
            Modifier
                .fillMaxHeight()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.TopCenter
            // verticalArrangement = Arrangement.Center
        ) {
//            Text(text = "{$categorySelected}")
            Spacer(modifier = Modifier.height(40.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                itemsIndexed(items = categoryItemList) {index, item ->
                    ExpenseIconButton(
                        item.icon,
                        item.label,
                        onClick = { categorySelected = item.label },
                        categorySelected
                    )
                }
            }
        }

        Box(
            modifier =
            Modifier
                .fillMaxHeight()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.BottomCenter
            // verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            keyboardNum()
        }
    }
}

@Composable
fun ExpenseIconButton(
    iconRes: Int,
    label: String,
    onClick: () -> Unit,
    selected: String?
) {
    var textcolor = if(label==selected) Color.Green else Color.Black
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
                    color = textcolor,
                )
            }
        }
    }
}

@Composable
fun ExpenseIconButton2(
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
                .aspectRatio(1f)
//                .align(Alignment.Start) // ปรับให้ชิดซ้าย
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

@Composable
fun keyboardNum() {
    var amount by remember {
        mutableStateOf("0")
    }

    @Composable
    fun NumberButton(number: String) {
        Button(
            onClick = {
                try {
                    amount += number
                    amount = amount.toInt().toString()
                }catch (_: Exception) {
                    amount = amount.toFloat().toString()
                }
            },
            modifier = Modifier
                .size(48.dp)
                .padding(5.dp),// เพิ่ม padding เพื่อเพิ่มขนาด
            contentPadding = PaddingValues(6.dp)
        ) {
            Text(number, fontSize = 16.sp)
        }
    }

    @Composable
    fun IconXButton() {
        Button(
            onClick = {
                if(amount.length>=1) {
                    amount = amount.substring(0, amount.length-1)
                }
            },
            modifier = Modifier
                .size(48.dp)
                .padding(5.dp), // เพิ่ม padding เพื่อเพิ่มขนาด
            contentPadding = PaddingValues(6.dp)
        ) {
            Icon(Icons.Default.Cancel, contentDescription = "Clear")
        }
    }



    Box(
        modifier = Modifier
//            .height(380.dp) // ปรับความสูงเล็กน้อย
            .background(color = Color.Green.copy(alpha = 0.1f)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp) // เพิ่มระยะห่างด้านล่าง
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
//                            .size(48.dp)
                            .padding(5.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        Text(text = "เพิ่มคำอธิบาย")
                    }

                    Text(
                        text = "บัญชีเริ่มต้น(บาท) $amount",
                        modifier = Modifier
//                            .size(25.dp)
                            .padding(5.dp)
                    )
                }
            }


            Box {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = { /* ระบุโค้ดที่ต้องการเมื่อคลิกปุ่ม */ },
                        modifier = Modifier.size(48.dp), // ปรับขนาดของปุ่ม
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        // ระบุไอคอนที่ต้องการแสดง
                        Text(text = "วันนี้")
                    }
                    NumberButton(number = "7")
                    NumberButton(number = "8")
                    NumberButton(number = "9")
                }
            }
            Box {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { /* ระบุโค้ดที่ต้องการเมื่อคลิกปุ่ม */ },
                        modifier = Modifier.size(48.dp) // ปรับขนาดของปุ่ม
                    ) {
                        // ระบุไอคอนที่ต้องการแสดง
                        Icon(Icons.Default.Book, contentDescription = "Booking")
                    }
                    NumberButton(number = "4")
                    NumberButton(number = "5")
                    NumberButton(number = "6")
                }
            }
            Box {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { /* ระบุโค้ดที่ต้องการเมื่อคลิกปุ่ม */ },
                        modifier = Modifier.size(48.dp) // ปรับขนาดของปุ่ม
                    ) {
                        // ระบุไอคอนที่ต้องการแสดง
                        Icon(Icons.Default.AddCircleOutline, contentDescription = "บวก")
                    }
                    NumberButton(number = "1")
                    NumberButton(number = "2")
                    NumberButton(number = "3")
                }
            }
            Box {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { /* ระบุโค้ดที่ต้องการเมื่อคลิกปุ่ม */ },
                        modifier = Modifier.size(48.dp) // ปรับขนาดของปุ่ม
                    ) {
                        // ระบุไอคอนที่ต้องการแสดง
                        Icon(Icons.Default.CheckCircle, contentDescription = "ถูก")
                    }
                    NumberButton(number = ".")
                    NumberButton(number = "0")
                    IconXButton()
                }
            }
        }
    }
}

@Composable
fun OperationButton(text: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .size(48.dp)
            .padding(5.dp), // เพิ่ม padding เพื่อเพิ่มขนาด
        contentPadding = PaddingValues(6.dp)
    ) {
        Text(text, fontSize = 16.sp)
    }
}

