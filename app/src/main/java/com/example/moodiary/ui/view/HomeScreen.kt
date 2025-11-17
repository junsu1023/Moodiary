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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.mapper.convertString
import com.example.moodiary.R
import com.example.moodiary.ui.components.EmotionCard
import com.example.moodiary.ui.components.RecommendationCard
import com.example.moodiary.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val diaries by homeViewModel.diaries.collectAsState()
    val recent = diaries.take(3)

    val todayDiaryExists = false
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
                                    text = item.timeStamp?.convertString() ?: "error",
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = item.content,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            // 우측 점수 배지
                            Text(
                                text = "${item.emotionScore}%",
                                color = Color.White,
                                modifier = Modifier
                                    .background(
                                        color = getScoreColor(item.emotionScore),
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