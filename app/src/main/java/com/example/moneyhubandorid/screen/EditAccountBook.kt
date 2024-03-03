package com.example.moneyhubandorid.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.Dataclass.AccountBook
import com.example.moneyhubandorid.api.MoneyHubAPI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountBookScreen(navController: NavHostController) {
    val data = navController.previousBackStackEntry?.savedStateHandle?.get<AccountBook>("data") ?:
    AccountBook(0, 0, "", 0, 0)

    val contextForToast = LocalContext.current
    val createClient = MoneyHubAPI.create()

    var idaccountbook by remember { mutableStateOf(data.idaccount_book) }
    var textFieldName by remember { mutableStateOf(data.account_book) }
    var textFieldImage by remember { mutableStateOf(data.account_photo_path) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
    }
}