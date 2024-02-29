package com.example.moneyhubandorid.screen.Finance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.AppBar.FinanceTopAppBar
import com.example.moneyhubandorid.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun income(navController: NavHostController){
    val contextForToast = LocalContext.current
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
            Text(text = "หน้าเพิ่มรายรับ")
            IncomeIconButtonRow() //ของปุ่มรายได้
            keyboardNum()
        }
    }
}

@Composable
fun IncomeIconButtonRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        ExpenseIconButton(
            iconRes = R.drawable.wages ,
            label = "ค่าจ้าง",
            onClick = { /*TODO*/ },
        )
        ExpenseIconButton(
            iconRes = R.drawable.earning,
            label = "โบนัส",
            onClick = { /*TODO*/ },
        )
        ExpenseIconButton(
            iconRes = R.drawable.investing,
            label = "การลงทุน",
            onClick = { /*TODO*/ },
        )
        ExpenseIconButton(
            iconRes = R.drawable.consultant,
            label = "ไอที",
            onClick = { /*TODO*/ }
        )
    }
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        ExpenseIconButton2(
            iconRes = R.drawable.settings,
            label = "การตั้งค่า",
            onClick = { /*TODO*/ }
        )
    }
}

