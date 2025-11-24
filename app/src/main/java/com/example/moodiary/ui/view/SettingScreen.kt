package com.example.moodiary.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moodiary.R
import com.example.moodiary.viewmodel.SettingsViewModel

@Composable

fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onLoggedOut: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val showLogoutDialog = uiState.isShowLogoutDialog

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SettingRow(
            title = stringResource(R.string.notifications),
            description = stringResource(R.string.notifications),
            trailing = {
                Switch(
                    checked = uiState.notificationsEnabled,
                    onCheckedChange = { viewModel.toggleNotifications() }
                )
            },
            onClick = { }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        SettingRow(
            title = stringResource(R.string.dark_mode),
            description = stringResource(R.string.dark_mode),
            trailing = {
                Switch(
                    checked = uiState.darkModeEnabled,
                    onCheckedChange = { viewModel.toggleDarkMode() }
                )
            },
            onClick = { }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        SettingRow(
            title = stringResource(R.string.change_password),
            description = stringResource(R.string.change_password),
            trailing = { },
            onClick = { }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        SettingRow(
            title = stringResource(R.string.logout),
            description = stringResource(R.string.logout),
            trailing = { },
            onClick = { viewModel.setDialogState(true) }
        )
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.setDialogState(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.setDialogState(false)
                        viewModel.logout(onLoggedOut)
                    }
                ) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.setDialogState(false) }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            title = {
                Text(text = stringResource(R.string.logout))
            },
            text = {
                Text(text = stringResource(R.string.logout_dialog_content))
            }
        )
    }
}

@Composable
private fun SettingRow(
    title: String,
    description: String,
    trailing: @Composable () -> Unit,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
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