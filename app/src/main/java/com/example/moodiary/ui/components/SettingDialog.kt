package com.example.moodiary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moodiary.state.DialogKind
import com.example.moodiary.R
import com.example.moodiary.ui.theme.MoodiaryCustomTheme
import com.example.moodiary.viewmodel.SettingViewModel

@Composable
fun SettingDialog(
    viewModel: SettingViewModel = hiltViewModel(),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val changePasswordDialogState by viewModel.changePasswordDialogState.collectAsState()
    val kind = uiState.dialogKind

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text(
                    text = stringResource(R.string.confirm),
                    color = MoodiaryCustomTheme.colors.fontColor1
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MoodiaryCustomTheme.colors.fontColor1
                )
            }
        },
        title = {
            Text(
                text = when(kind) {
                    is DialogKind.Logout -> stringResource(R.string.logout)
                    is DialogKind.ChangePassword -> stringResource(R.string.change_password)
                    is DialogKind.NoShow -> ""
                },
                color = MoodiaryCustomTheme.colors.fontColor1
            )
        },
        text = {
            when(kind) {
                is DialogKind.Logout -> {
                    Text(
                        text = stringResource(R.string.logout_dialog_content),
                        color = MoodiaryCustomTheme.colors.fontColor3
                    )
                }
                is DialogKind.ChangePassword -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = changePasswordDialogState.curPassword,
                            onValueChange = { viewModel.onChangeCurPassword(it) },
                            label = {
                                Text(
                                    text = stringResource(R.string.password),
                                    color = MoodiaryCustomTheme.colors.fontColor3
                                )
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Email
                            )
                        )

                        OutlinedTextField(
                            value = changePasswordDialogState.newPassword,
                            onValueChange = { viewModel.onChangeNewPassword(it) },
                            label = {
                                Text(
                                    text = stringResource(R.string.new_password),
                                    color = MoodiaryCustomTheme.colors.fontColor1
                                )
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Password
                            )
                        )

                        OutlinedTextField(
                            value = changePasswordDialogState.confirmNewPassword,
                            onValueChange = { viewModel.onChangeConfirmNewPassword(it) },
                            label = {
                                Text(
                                    text = stringResource(R.string.confirm_password),
                                    color = MoodiaryCustomTheme.colors.fontColor1
                                )
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password

                            )
                        )
                    }
                }
                is DialogKind.NoShow -> { }
            }
        },
        containerColor = MoodiaryCustomTheme.colors.cardColor
    )
}