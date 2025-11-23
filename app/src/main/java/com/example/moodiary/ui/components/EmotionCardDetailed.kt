package com.example.moodiary.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlin.math.min
import com.example.moodiary.R
import com.example.moodiary.ui.view.getScoreColor

@Composable
fun EmotionCardDetailed(
    title: String,
    score: Int,
    emotion: String,
    summary: String?,
    modifier: Modifier = Modifier
) {
    val graphColor = getScoreColor(score)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.width(92.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(76.dp)) {
                    val radius = min(size.width, size.height) / 2f
                    val stroke = radius * 0.28f
                    val padding = stroke / 2f
                    val startAngle = 150f
                    val fullSweep = 240f

                    drawArc(
                        color = graphColor.copy(alpha = 0.14f),
                        startAngle = startAngle,
                        sweepAngle = fullSweep,
                        useCenter = false,
                        topLeft = Offset(padding, padding),
                        size = Size((radius - padding) * 2f + padding, (radius - padding) * 2f + padding),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )

                    val sweep = fullSweep * (score.coerceIn(0, 100) / 100f)
                    drawArc(
                        color = graphColor,
                        startAngle = startAngle,
                        sweepAngle = sweep,
                        useCenter = false,
                        topLeft = Offset(padding, padding),
                        size = Size((radius - padding) * 2f + padding, (radius - padding) * 2f + padding),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                }

                Text(
                    text = "${score}%",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = getScoreColor(score)
                    ) {
                        Text(
                            text = emotion,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${stringResource(R.string.score)}: ${score}%",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (!summary.isNullOrBlank()) {
                    Text(
                        text = summary,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}