package com.example.moodiary

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.example.moodiary.ui.navigation.MoodiaryNavHost
import com.example.moodiary.ui.theme.MoodiaryTheme

@Composable
fun MoodiaryApp() {
    val navController = rememberNavController()

    MoodiaryTheme {
        Surface(
            color = colorResource(R.color.moodBackground)
        ) {
            MoodiaryNavHost(navController = navController)
        }
    }
}