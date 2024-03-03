package com.example.moneyhubandorid.screen.Finance

import android.content.Context
import android.media.RouteListingPreference
import android.preference.PreferenceActivity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.AppBar.FinanceTopAppBar
import com.example.moneyhubandorid.AppBar.SummmaryScreenTopAppBar
import com.example.moneyhubandorid.R
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.SharePreferencesManager
import kotlinx.coroutines.launch
import okhttp3.internal.http2.Header
import java.time.Month


@Composable
fun Summary(navController: NavHostController) {
    val contextForToast = LocalContext.current
    var categorySelected by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            SummmaryScreenTopAppBar(navController, contextForToast)
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
            Spacer(modifier = Modifier.height(40.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {

//                itemsIndexed(items = categoryItemList) {index, item ->
//                    ExpenseIconButton(
//                        item.icon,
//                        item.label,
//                        onClick = { categorySelected = item.label },
//                        categorySelected
//                    )
//                }
//            }

        }
            TotalMoney()
            NoteSummaryScreen()
//    val contextForToast = LocalContext.current.applicationContext
//    val scrollState = rememberScrollState()
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(5.dp)
//            .background( color = Color.Green.copy(alpha = 0.1f),
//                shape = RoundedCornerShape(16.dp)
//            )
//            .verticalScroll(scrollState)
//    ) {
//        // Header Button with Icon
////        ElevatedButton(
////            modifier = Modifier
////                .align(Alignment.TopEnd)
////                .padding(8.dp),
////            onClick = {
////                Toast.makeText(
////                    contextForToast,
////                    "Summary",
////                    Toast.LENGTH_LONG
////                ).show()
////            }
////        ) {
////            Row(
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Text(text = "การเงิน", color = Color.Black)
////            }
////        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    top = 250.dp,
//                    bottom = 20.dp
//                )
//        ) {
//            ElevatedButton(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 8.dp),
//                onClick = {
//                    if (navController.currentBackStack.value.size >= 2) {
//                        navController.popBackStack()
//                    }
//                    navController.navigate(Screen.Expense.route)
//                    Toast.makeText(
//                        contextForToast,
//                        "ค่าใช้จ่าย",
//                        Toast.LENGTH_LONG
//                    ).show()
//
//                }
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.shopping_cart), // Replace with your icon resource
//                        contentDescription = null, // Set a proper content description
//                        tint = Color.Black, // Icon color
//                        modifier = Modifier.size(24.dp) // Icon size
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(text = "+ ค่าใช้จ่าย", color = Color.Black)
//                }
//            }
//
//            ElevatedButton(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 8.dp),
//                onClick = {
//                    if (navController.currentBackStack.value.size >= 2) {
//                        navController.popBackStack()
//                    }
//                    navController.navigate(Screen.Income.route)
//                    Toast.makeText(
//                        contextForToast,
//                        "รายได้",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.money), // Replace with your icon resource
//                        contentDescription = null, // Set a proper content description
//                        tint = Color.Black, // Icon color
//                        modifier = Modifier.size(24.dp) // Icon size
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(text = "+ รายได้", color = Color.Black)
//                }
//            }
//        }
//
//        // Spacer to create separation
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Summary Text
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    top = 100.dp,
//                    bottom = 20.dp
//                )
//                .background(
//                    color = Color.Green.copy(alpha = 0.1f),
//                    shape = RoundedCornerShape(16.dp)
//                )
//                .padding(16.dp),
//            text = "ยอดรวมทุกบัญชี : 0 ฿\nรายได้ : 0 ฿\nค่าใช้จ่าย : 0 ฿",
//            textAlign = TextAlign.Center,
//            color = Color.Black
//        )
//
//        // Row with Buttons and Icons
//
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    top = 350.dp,
//                    bottom = 20.dp
//                )
//                .background(
//                    color = Color.Green.copy(alpha = 0.1f),
//                    shape = RoundedCornerShape(16.dp)
//                )
//                .padding(25.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Use a Box to position the image at the top-left corner
//            Box(
//                modifier = Modifier
//                    .size(60.dp)
//            ) {
//                // Icon
//                Image(
//                    painter = painterResource(id = R.drawable.book),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//
//            // Text
//            Text(
//                text = buildAnnotatedString {
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.Black,
//                            fontWeight = FontWeight.Bold
//                        )
//                    ) {
//                        append(" สมุดบันทึกเริ่มต้น\n")
//                    }
//
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.Black
//                        )
//                    ) {
//                        append(" จำนวนเงินทั้งหมด : 0 ฿\n")
//                    }
//
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.Black
//                        )
//                    ) {
//                        append(" รายได้ : 0 ฿\n")
//                        append(" ค่าใช้จ่าย : 0 ฿\n")
//                    }
//                },
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//    }
}
//@Composable
//fun OutlinedCardExample() {
//    OutlinedCard(
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface,
//        ),
//        border = BorderStroke(1.dp, Color.Black),
//        modifier = Modifier
//            .size(width = 240.dp, height = 100.dp)
//    ) {
//        Text(
//            text = "Outlined",
//            modifier = Modifier
//                .padding(16.dp),
//            textAlign = TextAlign.Center,
//
//        )
//    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SummaryScreenCenterBar(navController: NavHostController, contextForToast: Context) {
//    lateinit var sharePreferences: SharePreferencesManager
//    sharePreferences = SharePreferencesManager(contextForToast)
//    val userId = sharePreferences.userId ?: ""
//    var rememberVal by remember {
//        mutableStateOf(false)
//    }
//    var loggotDialog by remember { mutableStateOf(false) }
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//    val focusManager = LocalFocusManager.current
//    val coroutineScope = rememberCoroutineScope()
//    val keyboardController = LocalSoftwareKeyboardController.current
//    CenterAlignedTopAppBar(
//        title = {
//            /* ตัวอย่างเช่น */
//        },
//        actions = {
//            // Notifications icon
//            IconButton(
//                onClick = {
//                    keyboardController?.hide()
//                    focusManager.clearFocus()
//                    if (navController.currentBackStack.value.size >= 2) {
//                        navController.popBackStack()
//                    }
//                    navController.navigate(Screen.Finance.route)
//                },
//                modifier = Modifier.size(48.dp) // กำหนดขนาดของปุ่ม
//            ) {
//                Icon(Icons.Default.Cancel, contentDescription = null)
//            }
//            Spacer(modifier = Modifier.weight(1f))
//            // ปุ่ม "ค่าใช้จ่าย"
//            ElevatedButton(
//                onClick = {
//                    coroutineScope.launch {
//                    }
//                    if (navController.currentBackStack.value.size >= 2) {
//                        navController.popBackStack()
//                    }
//                    navController.navigate(Screen.Expense.route)
//                }
//            ) {
//                Text("ค่าใช้จ่าย",color = Color.Black)
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            // ปุ่ม "รายได้"
//            ElevatedButton(
//                onClick = {
//                    if (navController.currentBackStack.value.size >= 2) {
//                        navController.popBackStack()
//                    }
//                    navController.navigate(Screen.Income.route)
//                }
//            ) {
//                Text("รายได้",color = Color.Black)
//            }
//            Spacer(modifier = Modifier.weight(1f))
//            IconButton(
//                onClick = {
//                    /* ตัวอย่างเช่น */
//                },
//                modifier = Modifier.size(48.dp)
//            ) {
//                Icon(Icons.Default.Check, contentDescription = "รายได้")
//            }
//        },
//        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            containerColor = Color.Green.copy(alpha = 0.3f)
//        )
//    )
//}
}

@Composable
fun TotalMoney(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        Alignment.TopCenter
    ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "จำนวนเงินทั้งหมด \n" +
                            "รายได้\n" +
                            "ค่าใช้จ่าย",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
        }
}
}

@Composable
fun NoteSummaryScreen() {
        val BookNote = listOf("สมุดบันทีกเริ่มต้น", "กุมภาพันธ์", "มีนาคม")

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(25.dp)
        ,
        Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .size(425.dp),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = false,

        ) {
            itemsIndexed(items = BookNote) {index, item ->
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    // ใส่รูป

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.book),
                            contentDescription = "image",
                            modifier = Modifier.size(40.dp)
                        )

                        Text(
                            text = item,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    Text(
                        text = "จำนวนเงินทั้งหมด \n" +
                                "รายได้\n" +
                                "ค่าใช้จ่าย",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                        modifier = Modifier.padding(10.dp)
                    )
                }


            }
            }
        }
    }






