package com.example.moneyhubandorid

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moneyhubandorid.screen.Analysis.AnalysisScreen
import com.example.moneyhubandorid.screen.EditAccountBookScreen
import com.example.moneyhubandorid.screen.Finance.Expense
import com.example.moneyhubandorid.screen.Finance.FinanceScreen
import com.example.moneyhubandorid.screen.Finance.InsertAccountBookScreen
import com.example.moneyhubandorid.screen.Finance.income
import com.example.moneyhubandorid.screen.HomeScreen
import com.example.moneyhubandorid.screen.LoginScreen
import com.example.moneyhubandorid.screen.ProfileScreen
import com.example.moneyhubandorid.screen.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController)
        }

        composable(route = Screen.Register.route
        ) {
            RegisterScreen(navController)
        }

        composable(route = Screen.Profile.route
        ) {
            ProfileScreen(navController)
        }

        composable(route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }

        composable(route = Screen.Finance.route
        ) {
            FinanceScreen(navController)
        }

        composable(route = Screen.Analysis.route
        ) {
            AnalysisScreen(navController)
        }

        composable(route = Screen.Expense.route
        ) {
            Expense(navController)
        }

        composable(route = Screen.Income.route
        ) {
            income(navController)
        }

        composable(route = Screen.Summary.route
        ) {

        }

        composable(route = Screen.EditAccountBook.route
        ) {
            EditAccountBookScreen(navController)
        }

        composable(route = Screen.InsertAccountBook.route
        ) {
            InsertAccountBookScreen(navController)
        }


    }
}