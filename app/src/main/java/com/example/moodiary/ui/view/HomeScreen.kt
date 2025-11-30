package com.example.moodiary.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.mapper.convertString
import com.example.domain.model.DiaryModel
import com.example.moodiary.R
import com.example.moodiary.ui.components.EmotionCard
import com.example.moodiary.ui.components.RecommendationCard
import com.example.moodiary.ui.theme.MoodiaryCustomTheme
import com.example.moodiary.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    val diaries = uiState.diaries
    val todayDiaries = uiState.todayDiaries
    val recent = diaries.take(3)
    val emotionScores = uiState.todayDiaries.map { it.emotionScore }.average()
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
        color = MoodiaryCustomTheme.colors.background
    ) {
        Column(
            modifier = Modifier.verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EmotionCard(
                title = stringResource(R.string.today_mood_summary),
                score = emotionScores.toInt()
            )

            if (todayDiaries.isNotEmpty()) {
                RecommendationCard(
                    music = todayDiaries.map { it.musicUrl }.random(),
                    quote = todayDiaries.map { it.quote }.random()
                )
            } else {
                RecommendationCard(
                    music = null,
                    quote = null
                )
            }

            Text(
                text = stringResource(R.string.recent_diary),
                style = MaterialTheme.typography.titleMedium,
                color = MoodiaryCustomTheme.colors.fontColor1
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for(item in recent.take(3)) {
                    RecentDiaryCard(item)
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            if(uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun RecentDiaryCard(item: DiaryModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { /* TODO: 상세보기로 이동 */ }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MoodiaryCustomTheme.colors.cardColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = item.timeStamp?.convertString() ?: stringResource(R.string.error),
                    style = MaterialTheme.typography.bodySmall,
                    color = MoodiaryCustomTheme.colors.fontColor3
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.content,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    color = MoodiaryCustomTheme.colors.fontColor1
                )
            }

            Text(
                text = "${item.emotionScore}%",
                color = MoodiaryCustomTheme.colors.fontColor5,
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

@Composable
fun getScoreColor(score: Int): Color {
    return when {
        score >= 80 -> MoodiaryCustomTheme.colors.goodEmotionColor
        score >= 60 -> MoodiaryCustomTheme.colors.soSoEmotionColor
        score >= 40 -> MoodiaryCustomTheme.colors.warningEmotionColor
        else -> MoodiaryCustomTheme.colors.badEmotionColor
    }
}