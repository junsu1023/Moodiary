package com.example.moodiary.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moodiary.R
import com.example.moodiary.ui.theme.MoodiaryCustomTheme
import com.example.moodiary.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val uiState by forgotPasswordViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(MoodiaryCustomTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))

        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = null,
            tint = MoodiaryCustomTheme.colors.iconColor2,
            modifier = Modifier.size(88.dp)
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "가입한 이메일을 입력하면 비밀번호 재설정 링크를 보내드립니다.",
            style = MaterialTheme.typography.bodyMedium,
            color = MoodiaryCustomTheme.colors.fontColor1,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = {
                forgotPasswordViewModel.onUpdateEmail(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("이메일") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                unfocusedTextColor = MoodiaryCustomTheme.colors.fontColor6,
                focusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                unfocusedContainerColor = MoodiaryCustomTheme.colors.transparent,
                focusedIndicatorColor = MoodiaryCustomTheme.colors.borderColor1
            )
        )

        if(uiState.emailError != null) {
            Text(
                text = uiState.emailError!!,
                color = MoodiaryCustomTheme.colors.errorColor1,
                style = MoodiaryCustomTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 6.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MoodiaryCustomTheme.colors.buttonColor,
                contentColor = MoodiaryCustomTheme.colors.fontColor1
            )
        ) {
            if (uiState.isSending) {
                CircularProgressIndicator(
                    color = MoodiaryCustomTheme.colors.iconColor1,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = stringResource(R.string.send_resetting_link),
                    color = MoodiaryCustomTheme.colors.fontColor5
                )
            }
        }

        Spacer(Modifier.height(12.dp))
    }
}