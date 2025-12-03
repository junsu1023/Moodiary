package com.example.moodiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.moodiary.ui.theme.MoodiaryTheme
import com.example.moodiary.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashScreen = installSplashScreen()

        val isReady = splashViewModel.uiState.value.isReady
        splashScreen.setKeepOnScreenCondition { !isReady }

        setContent {
            val uiState by splashViewModel.uiState.collectAsState()

            MoodiaryTheme(darkTheme = uiState.isDarkMode) {
                MoodiaryApp(isLoggedIn = uiState.isLoggedIn)
            }
        }
    }
}