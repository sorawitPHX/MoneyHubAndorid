package com.example.moneyhubandorid

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route: String, val name: String, val icon: ImageVector) {
    object Login: Screen(route = "login_screen", name = "Login",icon = Icons.Default.Login)
    object Register: Screen(route = "register_screen", name = "Register",icon = Icons.Default.Add)
    object Profile: Screen(route = "profile_screen", name = "โปรไฟล์",icon = Icons.Default.Person)
    object Home: Screen(route = "home_screen", name = "หน้าแรก",icon = Icons.Default.Home)
    object Finance: Screen(route = "finance_screen", name = "การเงิน", icon = Icons.Default.Money)
    object Analysis: Screen(route = "analysis_screen", name = "การวิเคราะห์", icon = Icons.Default.Analytics)
    object Income: Screen(route = "income_screen", name = "Income", icon = Icons.Default.Money)
    object Expense: Screen(route = "Expense_screen", name = "Expense", icon = Icons.Default.Money)
    object Summary: Screen(route = "summary_screen", name = "Summary", icon = Icons.Default.MonetizationOn)
    object EditAccountBook: Screen(route = "editaccountbook_screen", name = "EditAccountBook", icon = Icons.Default.Construction)
    object InsertAccountBook: Screen(route = "insertaccountbook_screen", name = "InsertAccountBook", icon = Icons.Default.AddCard)
}