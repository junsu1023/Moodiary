package com.example.moodiary.ui.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Write : Screen("write")
    data object History : Screen("history")
    data object Settings : Screen("settings")
    data object Login : Screen("login")
    data object Signup : Screen("signup")
    data object ForgotPassword : Screen("forgot_password")
}