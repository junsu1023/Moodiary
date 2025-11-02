package com.example.moodiary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MoodiaryNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {

        }

        composable(Screen.Write.route) {

        }

        composable(Screen.History.route) {

        }

        composable(Screen.Settings.route) {

        }

        composable(Screen.Login.route) {

        }

        composable(Screen.Signup.route) {

        }
    }
}