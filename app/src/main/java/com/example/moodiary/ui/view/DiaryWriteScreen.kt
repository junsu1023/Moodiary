package com.example.moodiary.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.moodiary.ui.components.EmotionCard
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.moodiary.R

@Composable
fun DiaryWriteScreen(
    onBack: () -> Unit
) {
    var content by remember { mutableStateOf("") }
    // 간단 감정 분석: 글자수 비율로 0..100 점수 계산 (임시)
    val score = remember(content) {
        val len = content.trim().length
        (len.coerceAtMost(100)).coerceIn(0, 100)
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

            // 텍스트 입력창
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(4.dp)
            ) {
                BasicTextField(
                    value = content,
                    onValueChange = { content = it },
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = colorResource(R.color.black))
                )
            }

            // 저장 버튼
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    // TODO: 실제 저장 로직 추가
                    onBack()
                }) {
                    Text(text = stringResource(R.string.save))
                }
            }

            // 감정 분석 결과 (EmotionCard 재사용)
            EmotionCard(
                title = stringResource(R.string.analyze_mood_result),
                score = score
            )

            // 추천 음악 / 추천 글귀 카드
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.recommend_music),
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text(
                            text = "예시: 편안한 재즈 • Artist",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Card(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.recommend_phrase),
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text(
                            text = "예시: 오늘의 한줄 위로",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}