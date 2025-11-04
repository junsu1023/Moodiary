package com.example.moodiary.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moodiary.R

@Composable
fun RecommendationCard(
    music: String?,
    quote: String?,
    onMusicClick: (() -> Unit)? = null,
    onQuoteClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // 음악 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .then(if (music != null && onMusicClick != null) Modifier.clickable { onMusicClick() } else Modifier)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = null,
                    tint = colorResource(id = R.color.moodSecondary),
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Column {
                    Text(
                        text = stringResource(R.string.recommend_music),
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.moodNeutral)
                    )

                    Text(
                        text = music ?: stringResource(R.string.displayed_recommend_music),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // 글귀 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .then(if (quote != null && onQuoteClick != null) Modifier.clickable { onQuoteClick() } else Modifier)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = colorResource(id = R.color.moodPrimary),
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Column {
                    Text(
                        text = stringResource(R.string.recommend_phrase),
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.moodNeutral)
                    )

                    Text(
                        text = quote ?: stringResource(R.string.displayed_recommend_phrase),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}