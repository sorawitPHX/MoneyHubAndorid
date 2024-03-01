package com.example.moneyhubandorid.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.AppBar.FinanceTopAppBar
import com.example.moneyhubandorid.NavGraph
import com.example.moneyhubandorid.R
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.SharePreferencesManager
import com.example.moneyhubandorid.screen.Finance.ExpenseIconButton
import com.example.moneyhubandorid.screen.Finance.ExpenseIconButton2
import com.example.moneyhubandorid.screen.Finance.IncomeIconButtonRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val contextForToast = LocalContext.current.applicationContext
    lateinit var sharePreferences: SharePreferencesManager
    sharePreferences = SharePreferencesManager(context = contextForToast)

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                if (sharePreferences.isLoggedIn) {

                }
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "สมุดบันทึกเริ่มต้น")
                },
                actions = {
                    // Notifications Icon
//                    IconButton(
//                        onClick = {
//                            Toast.makeText(contextForToast, "Notifications", Toast.LENGTH_SHORT).show()
//                        }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Outlined.NotificationsNone,
//                            contentDescription = "Notifications"
//                        )
//                    }

                    // Home Icon
//                    IconButton(
//                        onClick = {
//                            Toast.makeText(contextForToast, "Home", Toast.LENGTH_SHORT).show()
//                        }
//                    ) {
//                        Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home")
//                    }

                    // Vertical 3 dots icon
//                    IconButton(
//                        onClick = {
//                            // handle click
//                        }
//                    ) {
//                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Open Menu")
//                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = "รายละเอียด",
                            modifier = Modifier
                                .background(Color.White) // กำหนดกรอบให้กับข้อความ
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Green.copy(alpha = 0.3f)
                )
            )
        },
        bottomBar = {
            BottomBar(navController, contextForToast)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            //verticlArangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "หน้าเพิ่มรายจ่าย")
            notebook_diary()
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
    var loggotDialog by remember { mutableStateOf(false) }
    var expanded by remember {
        mutableStateOf(false)
    }
    CenterAlignedTopAppBar(
        title = {
            Text(text = "สมุดบันทึกเริ่มต้น")
        },
        actions = {
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
                onDismissRequest = { expanded = false }
            ) {
                // Menu Items
                // Setting Icons
                DropdownMenuItem(
                    text = {
                        Text(text = "Settings")
                    },
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
                        Text(text = "Logout")
                    },
                    onClick = {
                        Toast.makeText(contextForToast, "Logout", Toast.LENGTH_SHORT).show()
                        expanded = false
                        loggotDialog = true
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Logout, contentDescription = null)
                    }
                )

                if (loggotDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            loggotDialog = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    loggotDialog = false
                                    if (rememberVal) {
                                        sharePreferences.clearUserLogin()
                                    } else {
                                        sharePreferences.clearUserAll()
                                    }
                                    if (navController.currentBackStack.value.size >= 2) {
                                        navController.popBackStack()
                                    }
                                    navController.navigate(Screen.Login.route)
                                }
                            ) {
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
                        title = { Text(text = "Logout") },
                        text = {
                            Column {
                                Text(text = "Do you want to logout?")
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = rememberVal,
                                        onCheckedChange = { rememberVal = it })
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


@Composable
fun TopAppBarHome (navController: NavHostController){
    val contextForToast = LocalContext.current

    Scaffold(
        topBar = {
            FinanceTopAppBar(navController, contextForToast)
        },
        bottomBar = {
            BottomBar(navController, contextForToast)
        },
        floatingActionButtonPosition = FabPosition.End,
    ){ paddingValues ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            //ของปุ่มรายจ่าย
            Text(text = "หน้าเพิ่มรรายจ่าย")
            notebook_diary()



            // Spacer to create separation
            Spacer(modifier = Modifier.height(16.dp))

            // Summary Text
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 100.dp,
                        bottom = 20.dp
                    )
                    .background(
                        color = Color.Green.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                text = "ยอดรวมทุกบัญชี : 0 ฿\nรายได้ : 0 ฿\nค่าใช้จ่าย : 0 ฿",
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            // Row with Buttons and Icons


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 350.dp,
                        bottom = 20.dp
                    )
                    .background(
                        color = Color.Green.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Use a Box to position the image at the top-left corner
                Box(
                    modifier = Modifier
                        .size(60.dp)
                ) {
                    // Icon
                    Image(
                        painter = painterResource(id = R.drawable.book),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Text
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(" สมุดบันทึกเริ่มต้น\n")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = Color.Black
                            )
                        ) {
                            append(" จำนวนเงินทั้งหมด : 0 ฿\n")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = Color.Black
                            )
                        ) {
                            append(" รายได้ : 0 ฿\n")
                            append(" ค่าใช้จ่าย : 0 ฿\n")
                        }
                    },
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        }
    }

@Composable
fun notebook_diary() {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.size(200.dp)
    ) {
        ExpenseIconButton(
            iconRes = R.drawable.book ,
            label = "สมุดบันทึกแล่ม1",
            onClick = { /*TODO*/ },
//            modifier = Modifier.size(100.dp) // กำหนดขนาดของปุ่ม
        )
        ExpenseIconButton(
            iconRes = R.drawable.story,
            label = "เพื่มสมุด",
            onClick = { /*TODO*/ },

        )
    }
}

@Composable
fun monthly_finances() {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.size(200.dp)
    ) {

        IconButton(
            onClick = {
            }
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Open Menu")
        }
    }
}


