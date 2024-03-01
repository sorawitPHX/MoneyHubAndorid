package com.example.moneyhubandorid.screen.Finance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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

    var categoryItemList = remember { mutableStateListOf<Category>(
        Category(R.drawable.wages,"ค่าจ้าง"),
        Category(R.drawable.earning,"โบนัส"),
        Category(R.drawable.investing,"การลงทุน"),
        Category(R.drawable.consultant,"ไอที"),
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