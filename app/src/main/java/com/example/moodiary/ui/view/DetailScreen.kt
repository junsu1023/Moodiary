package com.example.moodiary.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moodiary.ui.theme.MoodiaryCustomTheme
import com.example.moodiary.viewmodel.DetailViewModel
import com.example.moodiary.R
import com.example.moodiary.util.showToastMessage

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    diaryId: String
) {
    LaunchedEffect(diaryId) {
        detailViewModel.getDiary(diaryId)
    }

    val uiState by detailViewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.errorMessage) {
        if(uiState.errorMessage != null) {
            showToastMessage(context, uiState.errorMessage!!)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MoodiaryCustomTheme.colors.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if(uiState.isLoading) {
            CircularProgressIndicator()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = uiState.date,
                style = MoodiaryCustomTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MoodiaryCustomTheme.colors.fontColor6,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false),
                colors = CardDefaults.cardColors(
                    containerColor = MoodiaryCustomTheme.colors.cardColor
                )
            ) {
                Text(
                    text = uiState.content,
                    style = MoodiaryCustomTheme.typography.bodyLarge,
                    modifier = Modifier.padding(20.dp),
                    lineHeight = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.recommend_music),
                    style = MoodiaryCustomTheme.typography.labelLarge,
                    color = MoodiaryCustomTheme.colors.fontColor2
                )
                Text(
                    text = uiState.music,
                    style = MoodiaryCustomTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MoodiaryCustomTheme.colors.fontColor3
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.recommend_phrase),
                    style = MoodiaryCustomTheme.typography.labelLarge,
                    color = MoodiaryCustomTheme.colors.fontColor2
                )
                Text(
                    text = uiState.quote,
                    style = MoodiaryCustomTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MoodiaryCustomTheme.colors.fontColor3
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}