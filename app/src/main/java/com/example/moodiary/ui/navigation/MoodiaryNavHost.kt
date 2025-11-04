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
import com.example.moodiary.ui.view.DiaryWriteScreen
import com.example.moodiary.ui.view.HomeScreen

@Composable
fun MoodiaryNavHost(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route
    val onBack: () -> Unit = { navController.popBackStack() }

    Scaffold(
        topBar = {
            MoodTopBar(title = stringResource(R.string.app_name))
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        floatingActionButton = {
            if(currentRoute == Screen.Home.route) {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = { navController.navigate(Screen.Write.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Write Diary"
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
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

            }

            composable(Screen.Settings.route) {

            }

            composable(Screen.Login.route) {

            }

            composable(Screen.Signup.route) {

            }
        }
    }
}