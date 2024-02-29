package com.example.moneyhubandorid.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.LoginClass
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.api.MoneyHubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("NotConstructor", "RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    val contextForToast = LocalContext.current
    val Client = MoneyHubAPI.create()
    var id_careers = mapOf<String, Int>(
        "นักเรียน/นักศึกษา" to 1,
        "ธุรกิจส่วนตัว" to 2,
        "ข้าราชการ/พนักงานราชการ" to 3,
        "รับจ้าง" to 4
    )
    var id_genders = mapOf<String, Int>(
        "ชาย" to 1,
        "หญิง" to 2,
        "อื่นๆ" to 3
    )
    var idgender by remember {
        mutableIntStateOf(0)
    }
    var idcareer by remember {
        mutableIntStateOf(0)
    }
    var firstname by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var password_confirm by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    var careername by remember {
        mutableStateOf("")
    }
    var birthday by remember {
        mutableStateOf("")
    }

    var isButtonEnabled by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var logoutDialog by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "ลงทะเบียน", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = firstname,
            onValueChange = {
                firstname = it
                isButtonEnabled = validateInput(
                    firstname,
                    lastname,
                    birthday,
                    email,
                    password,
                    password_confirm,
                    careername,
                    gender
                )
            },
            label = { Text("ชื่อ") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = lastname,
            onValueChange = {
                lastname = it
                isButtonEnabled = validateInput(
                    firstname,
                    lastname,
                    birthday,
                    email,
                    password,
                    password_confirm,
                    careername,
                    gender
                )
            },
            label = { Text("นามสกุล") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isButtonEnabled = validateInput(
                    firstname,
                    lastname,
                    birthday,
                    email,
                    password,
                    password_confirm,
                    careername,
                    gender
                )
            },
            label = { Text("อีเมล") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isButtonEnabled = validateInput(
                    firstname,
                    lastname,
                    birthday,
                    email,
                    password,
                    password_confirm,
                    careername,
                    gender
                )
            },
            label = { Text("รหัสผ่าน") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password_confirm,
            onValueChange = {
                password_confirm = it
                isButtonEnabled = validateInput(
                    firstname,
                    lastname,
                    birthday,
                    email,
                    password,
                    password_confirm,
                    careername,
                    gender
                )
            },
            label = { Text("ยืนยันรหัสผ่าน") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        birthday = DateContent()

        gender = genderRadioGroupUsage()
        Spacer(modifier = Modifier.height(16.dp))

        careername = CareerDropdown()
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                keyboardController?.hide()
                focusManager.clearFocus()
                idcareer = id_careers[careername]!!
                idgender = id_genders[gender]!!
                Client.registerUser(
                    firstname,
                    lastname,
                    birthday,
                    email,
                    password,
                    idcareer,
                    idgender
                )
                    .enqueue(object : Callback<LoginClass> {

                        @SuppressLint("RestrictedApi")
                        override fun onResponse(
                            call: Call<LoginClass>,
                            response: Response<LoginClass>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    contextForToast,
                                    "Register successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                if (navController.currentBackStack.value.size >= 2) {
                                    navController.popBackStack()
                                }
                                navController.navigate(Screen.Login.route)
                            } else {
                                Toast.makeText(
                                    contextForToast,
                                    "Register Failed",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginClass>, t: Throwable) {
                            Toast.makeText(
                                contextForToast,
                                "Error inFailure ${t.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    })
            },
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("ลงทะเบียน")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    if (navController.currentBackStack.value.size >= 2) {
                        navController.popBackStack()
                    }
                    navController.navigate(Screen.Login.route)
                }
            ) {
                Text("มีบัญชีแล้ว?")
            }
        }
    }
}



@Composable
fun radioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit
) {
    Row(
    ) {
        mItems.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == item,
                    onClick = {
                        setSelected(item)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Magenta
                    )
                )
                Text(
                    text = item,
                    modifier = Modifier.padding(end = 1.dp)
                )
            }
        }
    }
}

@Composable
fun genderRadioGroupUsage(): String {
    val kinds = listOf("ชาย", "หญิง", "อื่นๆ")
    val (selected, setSelected) = remember {
        mutableStateOf(kinds[0])
    }
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "เพศ : $selected",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        radioGroup(
            mItems = kinds,
            selected,
            setSelected
        )
    }
    return selected
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateContent(): String {
    var calendar = Calendar.getInstance()
    var mYear = calendar.get(Calendar.YEAR)
    var mMonth = calendar.get(Calendar.MONTH)
    var mDay = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.set(mYear, mMonth, mDay)
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = calendar.timeInMillis
    )
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableLongStateOf(calendar.timeInMillis)
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    selectedDate = datePickerState.selectedDateMillis!!
                }) {
                    Text(text = "ยืนยัน")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "ยกเลิก")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = "วันเกิด"
        )
        FilledIconButton(
            onClick = { showDatePicker = true }) {
            Icon(
                modifier = Modifier.size(size = 25.dp),
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "Time Icon"
            )
        }
        var formatter = SimpleDateFormat("dd-MMM-yyy")
        Text(text = "วันที่ ${formatter.format(Date(selectedDate))}")
    }
    return SimpleDateFormat("yyyy-MM-dd").format(Date(selectedDate))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareerDropdown(): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    val CareerList = listOf(
        "นักเรียน/นักศึกษา",
        "ธุรกิจส่วนตัว",
        "ข้าราชการ/พนักงานราชการ",
        "รับจ้าง"
    )
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedCareer by remember {
        mutableStateOf(CareerList[0])
    }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .clickable { keyboardController?.hide() },
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }) {
        OutlinedTextField(
            modifier = Modifier
                .width(400.dp)
                .menuAnchor()
                .clickable { keyboardController?.hide() },
            value = selectedCareer,
            onValueChange = {},
            textStyle = TextStyle.Default.copy(fontSize = 12.sp),
            readOnly = true,
            label = { Text("อาชีพ") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            CareerList.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedCareer = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
    return selectedCareer
}

fun validateInput(
    firstname: String,
    lastname: String,
    birthday: String,
    email: String,
    password: String,
    password_confirm: String,
    career: String,
    gender: String
): Boolean {
    if (!(firstname.isNullOrBlank() ||
                lastname.isNullOrBlank() ||
                birthday.isNullOrBlank() ||
                email.isNullOrBlank() ||
                password.isNullOrBlank() ||
                password_confirm.isNullOrBlank() ||
                career.isNullOrBlank() ||
                gender.isNullOrBlank()) &&
        (password == password_confirm) &&
        (gender != "เลือกอาชีพ")
    ) {
        return true;
    } else {
        return false;
    }
}