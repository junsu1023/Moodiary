package com.example.moodiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.domain.usecase.GetDarkModeUseCase
import com.example.moodiary.ui.theme.MoodiaryTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getDarkModeUseCase: GetDarkModeUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isDarkMode by getDarkModeUseCase().collectAsState(initial = false)

            MoodiaryTheme(darkTheme = isDarkMode) {
                MoodiaryApp()
            }
        }
    }
}