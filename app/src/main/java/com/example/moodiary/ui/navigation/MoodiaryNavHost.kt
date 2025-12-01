package com.example.moodiary.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moodiary.R
import com.example.moodiary.ui.components.BottomBar
import com.example.moodiary.ui.components.MoodTopBar
import com.example.moodiary.ui.theme.MoodiaryCustomTheme
import com.example.moodiary.ui.view.DiaryWriteScreen
import com.example.moodiary.ui.view.EmotionHistoryScreen
import com.example.moodiary.ui.view.ForgotPasswordScreen
import com.example.moodiary.ui.view.HomeScreen
import com.example.moodiary.ui.view.LoginScreen
import com.example.moodiary.ui.view.SettingsScreen
import com.example.moodiary.ui.view.SignUpScreen

@Composable
fun MoodiaryNavHost(
    navController: NavHostController,
    startDestination: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route
    val onBack: () -> Unit = { navController.popBackStack() }
    val showBars = when(currentRoute) {
        Screen.Login.route, Screen.Signup.route, Screen.Write.route, Screen.ForgotPassword.route -> false
        else -> true
    }

    Scaffold(
        topBar = {
            if(showBars) {
                MoodTopBar(title = stringResource(R.string.app_name))
            }
        },
        bottomBar = {
            if(showBars) {
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            if(currentRoute == Screen.Home.route) {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = { navController.navigate(Screen.Write.route) },
                    containerColor = MoodiaryCustomTheme.colors.buttonColor
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Write Diary",
                        tint = MoodiaryCustomTheme.colors.iconColor1
                    )
                }
            }
        },
        containerColor = MoodiaryCustomTheme.colors.background
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }

            composable(Screen.Write.route) {
                DiaryWriteScreen(
                    onBack = onBack
                )
            }

            composable(Screen.History.route) {
                EmotionHistoryScreen()
            }

            composable(Screen.Settings.route) {
                SettingsScreen(
                    onLoggedOut = { navController.navigate(Screen.Login.route)}
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(
                    onLogin = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    },
                    onSignUp = { navController.navigate(Screen.Signup.route) },
                    onForgotPassword = { navController.navigate(Screen.ForgotPassword.route) }
                )
            }

            composable(Screen.Signup.route) {
                SignUpScreen(
                    onSignUpComplete = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Signup.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Screen.ForgotPassword.route) {
                ForgotPasswordScreen()
            }
        }
    }
}
