package com.example.moneyhubandorid

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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

//        composable(route = Screen.Home.route
//        ) {
//            HomeScreen(navController)
//        }
//
//        composable(route = Finance.Home.route
//        ) {
//            FinanceScreen(navController)
//        }
//
//        composable(route = Analysis.Home.route
//        ) {
//            AnalysisScreen(navController)
//        }



    }
}