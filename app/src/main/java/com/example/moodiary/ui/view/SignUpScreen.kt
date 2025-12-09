package com.example.moodiary.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moodiary.R
import com.example.moodiary.ui.theme.MoodiaryCustomTheme
import com.example.moodiary.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSignUpComplete: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState = signUpViewModel.uiState
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { signUpViewModel.updateEmail(it) },
            label = {
                Text(
                    text = stringResource(R.string.email),
                    color = MoodiaryCustomTheme.colors.fontColor6
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                unfocusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                focusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                unfocusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                focusedIndicatorColor = MoodiaryCustomTheme.colors.borderColor1
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = { signUpViewModel.updatePassword(it) },
            label = {
                Text(
                    text = stringResource(R.string.password),
                    color = MoodiaryCustomTheme.colors.fontColor6
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if(uiState.password.isNotEmpty()) {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Image(
                            painter = if (passwordVisible) painterResource(R.drawable.hide) else painterResource(R.drawable.show),
                            contentDescription = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(R.string.show_password)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                unfocusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                focusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                unfocusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                focusedIndicatorColor = MoodiaryCustomTheme.colors.borderColor1
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.confirmPassword,
            onValueChange = { signUpViewModel.updateConfirmPassword(it) },
            label = {
                Text(
                    text = stringResource(R.string.confirm_password),
                    color = MoodiaryCustomTheme.colors.fontColor6
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if(confirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if(uiState.confirmPassword.isNotEmpty()) {
                    IconButton(
                        onClick = { confirmVisible = !confirmVisible }
                    ) {
                        Image(
                            painter = if (confirmVisible) painterResource(R.drawable.hide) else painterResource(R.drawable.show),
                            contentDescription = if (confirmVisible) stringResource(R.string.hide_password) else stringResource(R.string.show_password)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                unfocusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                focusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                unfocusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                focusedIndicatorColor = MoodiaryCustomTheme.colors.borderColor1
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                signUpViewModel.signUp {
                    onSignUpComplete()
                }
            },
            enabled = uiState.email.isNotBlank() && uiState.password.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MoodiaryCustomTheme.colors.buttonColor,
                disabledContainerColor = MoodiaryCustomTheme.colors.buttonColor.copy(alpha = 0.3f)
            )
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                color = MoodiaryCustomTheme.colors.fontColor5
            )
        }
    }

    if(uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
