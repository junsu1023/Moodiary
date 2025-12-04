package com.example.moodiary.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodiary.ui.components.EmotionFilterChip
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.mapper.convertString
import com.example.moodiary.ui.components.EmotionCardDetailed
import com.example.moodiary.R
import com.example.moodiary.ui.theme.MoodiaryCustomTheme
import com.example.moodiary.viewmodel.HistoryViewModel

@Composable
fun EmotionHistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel(),
    onDiaryClick: (String) -> Unit
) {
    val uiState by historyViewModel.uiState.collectAsState()
    val diaries = uiState.diaries
    val context = LocalContext.current
    val allEmotions = listOf(stringResource(R.string.all), stringResource(R.string.good), stringResource(R.string.soso), stringResource(R.string.warning), stringResource(R.string.bad))
    val selectedFilter = uiState.filter
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .background(MoodiaryCustomTheme.colors.background),
        state = listState,
        verticalArrangement = spacedBy(12.dp),
    ) {
        item {
            Text(
                text = stringResource(R.string.mood_history),
                style = MoodiaryCustomTheme.typography.titleLarge,
                color = MoodiaryCustomTheme.colors.fontColor6
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                horizontalArrangement = spacedBy(8.dp)
            ) {
                for (emotion in allEmotions) {
                    EmotionFilterChip(
                        label = emotion,
                        selected = selectedFilter == emotion,
                        onClick = { historyViewModel.updateFilter(emotion) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        val filtered = when(selectedFilter) {
            context.getString(R.string.all) -> diaries
            context.getString(R.string.good) -> diaries.filter { it.emotionScore >= 80 }
            context.getString(R.string.soso) -> diaries.filter { it.emotionScore in 60 until 80 }
            context.getString(R.string.warning) -> diaries.filter { it.emotionScore in 40 until 60}
            else -> diaries.filter { it.emotionScore < 40 }
        }

        if (filtered.isEmpty()) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(R.drawable.no_filter_image),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MoodiaryCustomTheme.colors.fontColor6)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.no_diary_filtered),
                        style = MoodiaryCustomTheme.typography.bodyMedium,
                        color = MoodiaryCustomTheme.colors.fontColor6
                    )
                }
            }
        } else {
            items(filtered) { item ->
                EmotionCardDetailed(
                    title = item.timeStamp!!.convertString(),
                    score = item.emotionScore,
                    emotion = item.emotionScore.getEmotion(),
                    summary = item.content,
                    modifier = Modifier.fillMaxWidth(),
                    onDiaryClick = { onDiaryClick(item.diaryId) }
                )
            }
        }
    }
}

@Composable
fun Int.getEmotion(): String {
    return when(this) {
        in 80 .. 100 -> stringResource(R.string.good)
        in 60 until 80 -> stringResource(R.string.soso)
        in 40 until 60 -> stringResource(R.string.warning)
        else -> stringResource(R.string.bad)
    }
}