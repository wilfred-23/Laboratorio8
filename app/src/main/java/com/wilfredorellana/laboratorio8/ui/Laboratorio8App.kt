package com.wilfredorellana.laboratorio8.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wilfredorellana.laboratorio8.navigation.HomeRoute
import com.wilfredorellana.laboratorio8.navigation.LoginRoute
import com.wilfredorellana.laboratorio8.ui.screens.login.LoginScreen

@Composable
fun Laboratorio8App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(
                onLogin = {
                    navController.navigate(HomeRoute) {
                        popUpTo(route = LoginRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<HomeRoute> {
            MainScreen(
                onLogout = {
                    navController.navigate(LoginRoute) {
                        popUpTo(route = HomeRoute) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }
    }
}