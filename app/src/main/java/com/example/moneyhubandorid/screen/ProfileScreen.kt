package com.example.moneyhubandorid.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.Dataclass.ProfileClass
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.SharePreferencesManager
import com.example.moneyhubandorid.api.MoneyHubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("NotConstructor")
@Composable
fun ProfileScreen(navController: NavHostController) {
    lateinit var sharePreferences: SharePreferencesManager
    val contextForToast = LocalContext.current.applicationContext
    sharePreferences = SharePreferencesManager(contextForToast)
    val userEmail = sharePreferences.userEmail ?: ""
    val Client = MoneyHubAPI.create()
    var initialUser = ProfileClass(0, "", "", "","","","")
    var studentItems by remember {
        mutableStateOf(initialUser)
    }
    var loggotDialog by remember { mutableStateOf(false) }
    var rememberVal by remember {
        mutableStateOf(false)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                Client.getUser(userEmail)
                    .enqueue(object : Callback<ProfileClass> {
                        override fun onResponse(
                            call: Call<ProfileClass>,
                            response: Response<ProfileClass>
                        ) {
                            println(response.body())
                            if (response.isSuccessful) {
                                studentItems = response.body()!!
                            } else {
                                Toast.makeText(
                                    contextForToast,
                                    "UserEmail Not Found",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<ProfileClass>, t: Throwable) {
                            Toast.makeText(
                                contextForToast,
                                "Error inFailure ${t.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        }
    }

    Scaffold(
        topBar = {
//            FinanceTopAppBar(navController, contextForToast)
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(30.dp))
                Text(text = "โปรไฟล์", fontSize = 25.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "รหัสผู้ใช้:  ${studentItems.IDuser}\n" +
                            "ชื่อ:  ${studentItems.Firstname}\n" +
                            "นามสกุล  ${studentItems.Lastname}\n" +
                            "วันเกิด:  ${studentItems.Birthday.split("T")[0]}\n" +
                            "เพศ:  ${studentItems.Gender}\n" +
                            "อาชีพ:  ${studentItems.Career}\n",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                val isInvisible = studentItems.IDuser == 0
//                Box(
//                    content = {
//                        if (isInvisible) {
//                            Button(
//                                onClick = {
//                                    //Ass 10
//                                },
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(50.dp)
//                            ) {
//                                Text("Show all students")
//                            }
//                        }
//                    }
//                )
//                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        //ทำ Dialog
                        loggotDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                ) {
                    Text(text = "ลงชื่อออก")

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
                                    Text(text = "ตกลง")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    loggotDialog = false
                                }) {
                                    Text(text = "ไม่")
                                }
                            },
                            title = { Text(text = "ลงชื่ออก") },
                            text = {
                                Column {
                                    Text(text = "คุณต้องการลงชื่ออกหรือไม่?")
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = rememberVal,
                                            onCheckedChange = { rememberVal = it })
                                        Text(text = "จำ Email ของคุณไว้")
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}