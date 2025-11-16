package com.example.moodiary.ui.view

import android.widget.Toast
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moodiary.R
import com.example.moodiary.state.SaveState
import com.example.moodiary.viewmodel.WriteViewModel

@Composable
fun DiaryWriteScreen(
    onBack: () -> Unit,
    writeViewModel: WriteViewModel = hiltViewModel()
) {
    val uiState = writeViewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(uiState.saveState) {
        when(uiState.saveState) {
            SaveState.SUCCESS -> {
                Toast.makeText(context, context.getString(R.string.save_diary_success), Toast.LENGTH_SHORT).show()
                onBack()
            }
            SaveState.ERROR -> {
                println("test-kjs: uiSTate.errorMessage = ${uiState.errorMessage}")
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
            else -> {
                // Do not anything
            }
        }
    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = stringResource(R.string.write_diary),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )

            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(180.dp)
                    .padding(4.dp)
            ) {
                BasicTextField(
                    value = uiState.content,
                    onValueChange = { writeViewModel.onContentChanged(it) },
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize(),
                    textStyle = TextStyle(color = colorResource(R.color.black))
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        writeViewModel.saveDiary()
                    }
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }
        }
    }
}