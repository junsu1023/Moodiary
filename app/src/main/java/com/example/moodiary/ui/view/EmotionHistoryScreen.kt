package com.example.moodiary.ui.view

import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodiary.ui.components.EmotionFilterChip
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.ui.res.stringResource
import com.example.moodiary.ui.components.EmotionCardDetailed
import com.example.moodiary.R

data class EmotionEntry(val date: String, val score: Int, val emotion: String, val summary: String)

@Composable
fun EmotionHistoryScreen() {
    val allEmotions = listOf("모두", "행복", "슬픔", "분노", "불안", "평온")
    val entries = remember {
        listOf(
            EmotionEntry("2025-11-02", 78, "행복", "오늘은 친구와 산책하며 기분이 좋았다. 작은 성취를 느꼈다."),
            EmotionEntry("2025-11-01", 45, "슬픔", "오래된 기억이 떠올라 울적했다. 혼자서 정리하는 시간이 필요했다."),
            EmotionEntry("2025-10-30", 52, "불안", "내일 발표 때문에 잠을 설칠 정도로 긴장했다."),
            EmotionEntry("2025-10-28", 89, "행복", "오랫동안 기다리던 일이 잘 풀려서 매우 기뻤다."),
            EmotionEntry("2025-10-25", 30, "분노", "사소한 오해로 감정이 격해졌다. 바로 풀지 못해 답답했다."),
            EmotionEntry("2025-10-20", 66, "평온", "차분한 하루. 조용히 책을 읽으며 마음이 안정되었다.")
        )
    }

    var selectedFilter by remember { mutableStateOf("모두") }
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        state = listState,
        verticalArrangement = spacedBy(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.mood_history),
                style = MaterialTheme.typography.titleLarge
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
                        onClick = { selectedFilter = emotion }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        val filtered = if (selectedFilter == "모두") entries else entries.filter { it.emotion == selectedFilter }

        if (filtered.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.no_diary_filtered),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            items(filtered) { item ->
                EmotionCardDetailed(
                    title = item.date,
                    score = item.score,
                    emotion = item.emotion,
                    summary = item.summary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}