package com.example.moneyhubandorid

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moneyhubandorid.screen.AnalysisScreen
import com.example.moneyhubandorid.screen.Finance.Espense
import com.example.moneyhubandorid.screen.Finance.FinanceScreen
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

        composable(route = Screen.Espense.route
        ) {
            Espense(navController)
        }

        composable(route = Screen.Income.route
        ) {
            income(navController)
        }



    }
}