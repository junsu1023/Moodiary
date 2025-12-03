package com.example.moodiary

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.moodiary.ui.navigation.MoodiaryNavHost
import com.example.moodiary.ui.navigation.Screen

@Composable
fun MoodiaryApp(
    isLoggedIn: Boolean
) {
    val navController = rememberNavController()
    val startDestination = if(isLoggedIn) Screen.Home.route else Screen.Login.route

    MoodiaryNavHost(
        navController = navController,
        startDestination = startDestination
    )
}