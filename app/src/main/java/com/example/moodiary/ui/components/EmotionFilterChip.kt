package com.example.moodiary.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import com.example.moodiary.R

@Composable
fun EmotionFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else Color(0xFFF0F0F0),
        modifier = Modifier
            .padding(end = 8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Text(
            text = label,
            color = if (selected) colorResource(R.color.white) else colorResource(R.color.black),
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
            style = MaterialTheme.typography.bodySmall
        )
    }
}