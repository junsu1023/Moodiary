package com.example.moodiary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.moodiary.ui.navigation.MoodiaryNavHost
import com.example.moodiary.ui.navigation.Screen
import com.example.moodiary.viewmodel.InitViewModel

@Composable
fun MoodiaryApp(
    initViewModel: InitViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isLoggedIn by initViewModel.isLoggedIn.collectAsState()
    val startDestination = if(isLoggedIn) Screen.Home.route else Screen.Login.route

    MoodiaryNavHost(
        navController = navController,
        startDestination = startDestination
    )
}