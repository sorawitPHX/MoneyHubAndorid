package com.example.moneyhubandorid.screen.Finance

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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Espense(navController: NavHostController) {
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

    Scaffold(
        topBar = {
            FinanceTopAppBar(navController, contextForToast)
        },
        bottomBar = {
            BottomBar(navController, contextForToast)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
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
            Text(text = "หน้าเพิ่มรายจ่าย")
            ExpenseIconButtonRow()
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
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ExpenseIconButton2(
            iconRes = R.drawable.settings,
            label = "การตั้งค่า",
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









