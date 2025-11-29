package com.example.moodiary.ui.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moodiary.R
import com.example.moodiary.ui.theme.MoodiaryCustomTheme

@Composable
fun RecommendationCard(
    music: String?,
    quote: String?,
    onMusicClick: (() -> Unit)? = null,
    onQuoteClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .then(if (music != null && onMusicClick != null) Modifier.clickable { onMusicClick() } else Modifier),
            colors = CardDefaults.cardColors(
                containerColor = MoodiaryCustomTheme.colors.cardColor
            )
        ) {
            CardItem(
                painter = painterResource(R.drawable.music),
                title = stringResource(R.string.recommend_music),
                content = music ?: stringResource(R.string.displayed_recommend_music),
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .then(if (quote != null && onQuoteClick != null) Modifier.clickable { onQuoteClick() } else Modifier),
            colors = CardDefaults.cardColors(
                containerColor = MoodiaryCustomTheme.colors.cardColor
            )
        ) {
            CardItem(
                imageVector = Icons.Default.Favorite,
                title = stringResource(R.string.recommend_phrase),
                content = quote ?: stringResource(R.string.displayed_recommend_phrase)
            )
        }
    }
}

@Composable
fun CardItem(
    painter: Painter? = null,
    imageVector: ImageVector? = null,
    title: String,
    content: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (painter == null) {
            Icon(
                imageVector = imageVector!!,
                contentDescription = null,
                tint = colorResource(id = R.color.moodSecondary),
                modifier = Modifier.size(28.dp)
            )
        } else {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = colorResource(id = R.color.moodSecondary),
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MoodiaryCustomTheme.colors.fontColor3
            )

            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                modifier = Modifier.basicMarquee(),
                color = MoodiaryCustomTheme.colors.fontColor
            )
        }
    }
}
