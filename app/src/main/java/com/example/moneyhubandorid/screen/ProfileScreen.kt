package com.example.moneyhubandorid.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.ProfileClass
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.SharePreferencesManager
import com.example.moneyhubandorid.api.StudentAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("NotConstructor")
@Composable
fun ProfileScreen(navController: NavHostController) {
    lateinit var sharePreferences: SharePreferencesManager
    val contextForToast = LocalContext.current.applicationContext
    sharePreferences = SharePreferencesManager(contextForToast)
    val userId = sharePreferences.userId ?: ""
    val Client = StudentAPI.create()
    val initialStudent = ProfileClass("", "", "", "")
    var studentItems by remember {
        mutableStateOf(initialStudent)
    }
    var loggotDialog by remember{mutableStateOf(false)}
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
                Client.searchStudent(userId)
                    .enqueue(object : Callback<ProfileClass> {
                        override fun onResponse(
                            call: Call<ProfileClass>,
                            response: Response<ProfileClass>
                        ) {
                            if (response.isSuccessful) {
                                studentItems = ProfileClass(
                                    response.body()!!.std_id, response.body()!!.std_name,
                                    response.body()!!.std_gender, response.body()!!.role
                                )
                            } else {
                                Toast.makeText(
                                    contextForToast,
                                    "Student ID Not Found",
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Profile", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Student ID: $userId\nName: ${studentItems.std_name}\n" +
                    "Gender: ${studentItems.std_gender}\nRole: ${studentItems.role}",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        val isInvisible = studentItems.role == "admin"
        Box(
            content = {
                if (isInvisible) {
                    Button(
                        onClick = {
                            //Ass 10
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Show all students")
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                //ทำ Dialog
                loggotDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Logout")

            if(loggotDialog) {
                AlertDialog(
                    onDismissRequest = {
                        loggotDialog = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                loggotDialog = false
                                if(rememberVal) {
                                    sharePreferences.clearUserLogin()
                                }else {
                                    sharePreferences.clearUserAll()
                                }
                                if (navController.currentBackStack.value.size >= 2) {
                                    navController.popBackStack()
                                }
                                navController.navigate(Screen.Login.route)
                            }
                        ){
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
                    title = {Text(text = "Logout")},
                    text = {
                        Column {
                            Text(text = "Do you want to logout?")
                            Row (
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Checkbox(checked = rememberVal, onCheckedChange = { rememberVal = it })
                                Text(text = "Remember your student ID")
                            }
                        }
                    }
                )
            }
        }

    }


}