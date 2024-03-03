package com.example.moneyhubandorid.screen

import android.annotation.SuppressLint
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.LoginClass
import com.example.moneyhubandorid.R
import com.example.moneyhubandorid.api.MoneyHubAPI
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.SharePreferencesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("NotConstructor", "RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var userEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val Client = MoneyHubAPI.create()
    val contextForToast = LocalContext.current.applicationContext
    var userItems = remember { mutableStateListOf<LoginClass>() }
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
                    navController.navigate(Screen.Home.route)
                }
                if (!sharePreferences.userEmail.isNullOrEmpty()) {
                    userEmail = sharePreferences.userEmail ?: ""
                }
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Money Hub",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mobile_moneyhub),
                contentDescription = "Login image",
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp),
                contentScale = ContentScale.Fit
            )


            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Sign In",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(10.dp))


                OutlinedTextField(
                    value = userEmail,
                    onValueChange = {
                        userEmail = it
                        isButtonEnabled = !userEmail.isNullOrBlank() && !password.isNullOrEmpty()
                    },
                    label = { Text(text = "อีเมล") },
                    placeholder = { Text(text = "Email Address") },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isButtonEnabled = !userEmail.isNullOrBlank() && !password.isNullOrEmpty()
                    },

                    label = { Text("รหัสผ่าน") },
                    placeholder = { Text(text = "Password") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),

                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )



                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        Client.loginUser(userEmail, password)
                            .enqueue(object : Callback<LoginClass> {

                                @SuppressLint("RestrictedApi")
                                override fun onResponse(
                                    call: Call<LoginClass>,
                                    response: Response<LoginClass>
                                ) {
                                    println(response.body())
                                    if (response.isSuccessful) {
                                        if (response.body()!!.success == 1) {
                                            sharePreferences.isLoggedIn = true
                                            sharePreferences.userId = response.body()!!.iduser
                                            sharePreferences.userEmail = response.body()!!.email
                                            Toast.makeText(
                                                contextForToast,
                                                "Login successfully",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            if (navController.currentBackStack.value.size >= 2) {
                                                navController.popBackStack()
                                            }
                                            navController.navigate(Screen.Home.route)
                                        } else {
                                            Toast.makeText(
                                                contextForToast,
                                                "Email or Password is incorrect",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } else {
                                        userItems.clear()
                                        Toast.makeText(
                                            contextForToast,
                                            "Email Not Found",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<LoginClass>, t: Throwable) {
                                    Toast.makeText(
                                        contextForToast,
                                        "Login inFailure ${t.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    println("Login inFailure " + t.message)
                                }
                            })
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
//            Spacer(modifier = Modifier.padding(20.dp))
                    Text(text = "เข้าสู่ระบบ", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("คุณยังไม่มีบัญชีใช่หรือไม่?")
                    TextButton(
                        onClick = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            if (navController.currentBackStack.value.size >= 2) {
                                navController.popBackStack()
                            }
                            navController.navigate(Screen.Register.route)
                        }
                    ) {
                        Text("ลงทะเบียน")
                    }
//            Text(text = "${sharePreferences.isLoggedIn}")
                }
            }
        }
    }
}
