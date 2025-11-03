package com.example.moodiary.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moodiary.R
import com.example.moodiary.ui.components.EmotionCard
import com.example.moodiary.ui.components.RecommendationCard

data class DiaryPreview(val id: String, val date: String, val snippet: String, val score: Int)

@Composable
fun HomeScreen() {
    val recent = listOf(
        DiaryPreview("1", "2025-11-02", "오늘은 날씨가 좋아서 산책을 했어...", 78),
        DiaryPreview("2", "2025-10-30", "새로운 프로젝트를 시작했고 긴장돼...", 52),
        DiaryPreview("3", "2025-10-28", "친구와 맛있는 저녁을 먹었어.", 89)
    )

    val todayDiaryExists = true
    val emotionScores = listOf(40f, 55f, 60f, 70f, 65f)
    val avgScore = if (emotionScores.isNotEmpty()) emotionScores.average().toFloat() else 0f
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EmotionCard(
                title = stringResource(R.string.today_mood_summary),
                score = avgScore.toInt()
            )

            if (todayDiaryExists) {
                RecommendationCard(
                    music = "Lo-fi Chillbeat - Calm Evening", // 임시
                    quote = "작은 걸음들이 큰 길을 만든다."
                )
            } else {
                RecommendationCard(
                    music = null,
                    quote = null
                )
            }

            Text(
                text = stringResource(R.string.recent_diary),
                style = MaterialTheme.typography.titleMedium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for(item in recent.take(3)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { /* TODO: 상세보기로 이동 */ }
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = item.date,
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = item.snippet,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            // 우측 점수 배지
                            Text(
                                text = "${item.score}%",
                                color = Color.White,
                                modifier = Modifier
                                    .background(
                                        color = getScoreColor(item.score),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 6.dp
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

// 점수에 따른 색상 매핑
private fun getScoreColor(score: Int): Color {
    return when {
        score >= 80 -> Color(0xFF4CAF50) // green
        score >= 60 -> Color(0xFFFFC107) // amber
        score >= 40 -> Color(0xFFFF9800) // orange
        else -> Color(0xFFF44336)        // red
    }
}