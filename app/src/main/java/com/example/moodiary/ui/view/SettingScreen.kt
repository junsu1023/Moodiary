package com.example.moodiary.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodiary.R
import com.example.moodiary.ui.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel= viewModel(),
    onBack: () -> Unit,
    onLoggedOut: () -> Unit
) {
    val notifications by viewModel.notificationsEnabled.collectAsState()
    val darkMode by viewModel.darkModeEnabled.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // 알림 설정
        SettingRow(
            title = stringResource(R.string.notifications),
            description = stringResource(R.string.notifications),
            trailing = {
                Switch(
                    checked = notifications,
                    onCheckedChange = { viewModel.toggleNotifications() }
                )
            }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // 다크모드 토글
        SettingRow(
            title = stringResource(R.string.dark_mode),
            description = stringResource(R.string.dark_mode),
            trailing = {
                Switch(
                    checked = darkMode,
                    onCheckedChange = { viewModel.toggleDarkMode() }
                )
            }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // 비밀번호 변경
        SettingRow(
            title = stringResource(R.string.change_password),
            description = stringResource(R.string.change_password),
            trailing = { }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // 앱 정보
        SettingRow(
            title = stringResource(R.string.app_info),
            description = stringResource(R.string.app_info),
            trailing = { }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // 로그아웃
        SettingRow(
            title = stringResource(R.string.logout),
            description = stringResource(R.string.logout),
            trailing = { }
        )
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.logout {
                            showLogoutDialog = false
                            onLoggedOut()
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            title = {
                Text(text = stringResource(R.string.logout))
                    },
            text = {
                Text(text = stringResource(R.string.logout))
            }
        )
    }
}

@Composable
private fun SettingRow(
    title: String,
    description: String,
    trailing: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            supportingContent = {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingContent = {
                trailing()
            }
        )
    }
}