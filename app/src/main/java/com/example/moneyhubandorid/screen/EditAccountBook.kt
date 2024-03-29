package com.example.moneyhubandorid.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.Dataclass.AccountBook
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.api.MoneyHubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountBookScreen(navController: NavHostController) {
    val data = navController.previousBackStackEntry?.savedStateHandle?.get<AccountBook>("data") ?:
    AccountBook(0, 0, "", 0, 0)

    val contextForToast = LocalContext.current
    val createClient = MoneyHubAPI.create()

    var idaccountbook by remember { mutableStateOf(data.idaccount_book) }
    var textFieldName by remember { mutableStateOf(data.account_book) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Edit Account Book",
            fontSize = 25.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = textFieldName,
            onValueChange = { textFieldName = it },
            label = { Text("Account Name") }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(modifier = Modifier
                .width(100.dp),
                onClick = {
                    createClient.updateAccountBook(
                        idaccountbook, textFieldName
                    ).enqueue(object : Callback<AccountBook> {
                        override fun onResponse(
                            call: Call<AccountBook>,
                            response: Response<AccountBook>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(contextForToast, "Successfully Updated",
                                    Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(contextForToast, "Update Failure",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<AccountBook>, t: Throwable) {
                            Toast.makeText(contextForToast, "Error onFailure " + t.message,
                                Toast.LENGTH_LONG).show()
                        }
                    })
                    if (navController.currentBackStack.value.size >= 2) {
                        navController.popBackStack()
                    }
                    navController.navigate(Screen.Home.route)
                }
            ) {
                Text("Update")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier
                .width(100.dp),
                onClick = {
                    if (navController.currentBackStack.value.size >= 2) {
                        navController.popBackStack()
                    }
                    navController.navigate(Screen.Home.route)
                }) {
                Text("Cancel")
            }
        }
    }
}