package com.example.moodiary

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moodiary.ui.components.BottomBar
import com.example.moodiary.ui.components.MoodTopBar
import com.example.moodiary.ui.navigation.MoodiaryNavHost
import com.example.moodiary.ui.navigation.Screen
import com.example.moodiary.ui.theme.MoodiaryTheme

@Composable
fun MoodiaryApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    MoodiaryTheme {
        Surface(
            color = colorResource(R.color.moodBackground)
        ) {
            Scaffold(
                topBar = {
                    MoodTopBar(title = stringResource(R.string.app_name))
                },
                bottomBar = { BottomBar(navController = navController) },
                floatingActionButton = {
                    if(currentRoute == Screen.Home.route) {
                        FloatingActionButton(
                            shape = CircleShape,
                            onClick = { navController.navigate(Screen.Write) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Write Diary"
                            )
                        }
                    }
                }
            ) { innerPadding ->
                MoodiaryNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}