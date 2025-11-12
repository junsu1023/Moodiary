package com.example.moodiary

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.moodiary.ui.navigation.MoodiaryNavHost
import com.example.moodiary.ui.navigation.Screen
import com.example.moodiary.ui.theme.MoodiaryTheme
import com.example.moodiary.viewmodel.InitViewModel

@Composable
fun MoodiaryApp(
    initViewModel: InitViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isLoggedIn by initViewModel.isLoggedIn.collectAsState()
    val startDestination = if(isLoggedIn) Screen.Home.route else Screen.Login.route

    MoodiaryTheme {
        Surface(
            color = colorResource(R.color.moodBackground)
        ) {
            MoodiaryNavHost(
                navController = navController,
                startDestination = startDestination
            )
        }
    }
}