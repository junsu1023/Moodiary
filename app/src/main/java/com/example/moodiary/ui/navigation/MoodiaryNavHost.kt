package com.example.moodiary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moodiary.ui.view.HomeScreen

@Composable
fun MoodiaryNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
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