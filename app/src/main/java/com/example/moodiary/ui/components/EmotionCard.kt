package com.example.moodiary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Canvas
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodiary.R
import com.example.moodiary.ui.theme.MoodiaryCustomTheme

@Composable
fun EmotionCard(title: String, score: Int) {
    val activeStrokeColor = MoodiaryCustomTheme.colors.activeStrokeColor
    val inActiveStrokeColor = MoodiaryCustomTheme.colors.inactiveStrokeColor

    val clamped = score.coerceIn(0, 100)
    val sweepTotal = 300f            // 보이는 아크 총 각도 (하단에 gap)
    val startAngle = 120f           // 시작 각도 (결과적으로 gap이 하단 중앙에 위치)
    val strokeWidth = 18f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MoodiaryCustomTheme.colors.cardColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MoodiaryCustomTheme.colors.fontColor2
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.size(140.dp)
                ) {
                    val diameter = size.minDimension
                    val inset = strokeWidth / 2f

                    // 배경 아크 (회색)
                    drawArc(
                        color = inActiveStrokeColor,
                        startAngle = startAngle,
                        sweepAngle = sweepTotal,
                        useCenter = false,
                        topLeft = Offset(inset, inset),
                        size = Size(diameter - strokeWidth, diameter - strokeWidth),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )

                    // 전경 아크 (점수 비율)
                    val foregroundSweep = (clamped / 100f) * sweepTotal
                    drawArc(
                        color = activeStrokeColor,
                        startAngle = startAngle,
                        sweepAngle = foregroundSweep,
                        useCenter = false,
                        topLeft = Offset(inset, inset),
                        size = Size(diameter - strokeWidth, diameter - strokeWidth),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if(clamped == 0) "?" else "$clamped",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MoodiaryCustomTheme.colors.fontColor2
                    )

                    Text(
                        text = stringResource(R.string.score),
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 12.sp,
                        color = MoodiaryCustomTheme.colors.fontColor1
                    )
                }
            }
        }
    }
}