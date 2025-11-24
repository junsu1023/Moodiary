package com.example.moodiary.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moodiary.R
import com.example.moodiary.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val state = loginViewModel.uiState
    val context = LocalContext.current
    val passwordVisible = remember { mutableStateOf(false) }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLogin()
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = state.email,
            onValueChange = { loginViewModel.onEmailChange(it) },
            label = {
                Text(text = stringResource(R.string.id))
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { loginViewModel.onPasswordChange(it) },
            label = {
                Text(text = stringResource(R.string.password))
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (state.password.isNotEmpty()) {
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Image(
                            painter = if (passwordVisible.value) painterResource(R.drawable.hide) else painterResource(R.drawable.show),
                            contentDescription = if (passwordVisible.value) stringResource(R.string.hide_password) else stringResource(R.string.show_password)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                loginViewModel.login()
            },
            enabled = state.email.isNotBlank() && state.password.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.login))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(
                onClick = onSignUp
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            TextButton(
                onClick = onForgotPassword
            ) {
                Text(
                    text = stringResource(R.string.find_password),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}