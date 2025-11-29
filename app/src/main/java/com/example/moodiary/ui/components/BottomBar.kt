package com.example.moodiary.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodiary.ui.navigation.Screen
import com.example.moodiary.ui.theme.MoodiaryCustomTheme

data class BottomNavItem(val route: String, val title: String, val icon: @Composable () -> Unit)

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(Screen.Home.route, "홈") { Icon(Icons.Default.Home, contentDescription = null) },
        BottomNavItem(Screen.History.route, "기록") { Icon(Icons.Default.Favorite, contentDescription = null) },
        BottomNavItem(Screen.Settings.route, "설정") { Icon(Icons.Default.Settings, contentDescription = null) }
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding()
            .height(56.dp),
        containerColor = MoodiaryCustomTheme.colors.background
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MoodiaryCustomTheme.colors.fontColor,
                    unselectedIconColor = MoodiaryCustomTheme.colors.fontColor3
                )
            )
        }
    }
}
