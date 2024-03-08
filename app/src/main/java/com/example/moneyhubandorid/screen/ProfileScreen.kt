package com.example.moneyhubandorid.screen

import android.annotation.SuppressLint
import android.os.Build

import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility

import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.Dataclass.ProfileClass
import com.example.moneyhubandorid.R
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.SharePreferencesManager
import com.example.moneyhubandorid.api.MoneyHubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date
import java.util.Locale

@SuppressLint("NotConstructor", "RestrictedApi")
@Composable
fun ProfileScreen(navController: NavHostController) {
    lateinit var sharePreferences: SharePreferencesManager
    val contextForToast = LocalContext.current.applicationContext
    sharePreferences = SharePreferencesManager(contextForToast)
    val userEmail = sharePreferences.userEmail ?: ""
    val Client = MoneyHubAPI.create()
    var initialUser = ProfileClass(0, "", "", "","","","")
    var studentItems by remember { mutableStateOf(initialUser) }
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
            // FinanceTopAppBar(navController, contextForToast)
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
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF7DFFCC), Color(0xFFD1FFAD)),
                        startY = 1f,
                        endY = LocalDensity.current.run { 500.dp.toPx() }
                    )
                )
                .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(
                        bottom = 10.dp
                    ),
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(0.8f),
                                    Color.White.copy(0.7f),
                                    Color.White.copy(0.3f),
                                    Color.White.copy(0.2f),
                                    Color.White.copy(0.2f)
                                ),
                                startY = 0f,
                                endY = 1000f
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp).padding(start = 10.dp, top = 23.dp)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = 47.dp,
                                top = 20.dp,
                                bottom = 9.dp
                            ),
                        text = "โปรไฟล์",
                        fontSize = 33.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Icon",
                modifier = Modifier.size(187.dp)
            )
            Spacer(modifier = Modifier.height(17.dp))

            Text(
                text = "${studentItems.Firstname} " + " ${studentItems.Lastname}",
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Magenta
            )
            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "ข้อมูลส่วนตัว",fontWeight = FontWeight.SemiBold,
                    fontSize =23.sp)
                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 13.dp, end = 13.dp
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(1f),
                                    Color.White.copy(0.9f),
                                    Color.White.copy(0.7f),
                                    Color.White.copy(0.5f),
                                    Color.White.copy(0.4f)
                                ),
                                startY = 0f,
                                endY = 1000f
                            ),
                            shape = RoundedCornerShape(25.dp)
                        )
                        .padding(25.dp),
                    text = "ชื่อ :  ${studentItems.Firstname}\n" +
                            "นามสกุล :  ${studentItems.Lastname}\n" +
                            "วันเกิด :  ${studentItems.Birthday}\n" +
                            "เพศ :  ${studentItems.Gender}\n" +
                            "อาชีพ :  ${studentItems.Career}\n",
                    fontSize = 21.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(30.dp))

                val isInvisible = studentItems.IDuser == 0

                Button(
                    onClick = {
                        // ทำ Dialog
                        loggotDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.exit),
                        contentDescription = "Icon",
                        modifier = Modifier.size(32.dp).padding(end = 8.dp)
                    )
                    Text(
                        text = "ลงชื่อออก",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                AnimatedVisibility(
                    visible = loggotDialog,
                    enter = slideInVertically(
                        initialOffsetY = { it }
                    ) + expandVertically(
                        expandFrom = Alignment.Top
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { it }
                    ) + shrinkVertically(
                        shrinkTowards = Alignment.Top
                    )
                ) {
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
                                Text(text = "ยกเลิก")
                            }
                        },
                        title = { Text(text = "ลงชื่อออก") },
                        text = {
                            Column {
                                Text(text = "คุณต้องการลงชื่อออกหรือไม่?",fontSize = 18.sp)
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = rememberVal,
                                        onCheckedChange = { rememberVal = it })
                                    Text(text = "จำ Email ของคุณไว้",fontSize = 16.sp)
                                }
                            }
                        }
                    )
                }

            }
        }
    }
}
