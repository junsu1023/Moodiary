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
import androidx.compose.runtime.remember
import com.example.moodiary.ui.theme.MoodiaryCustomTheme

@Composable
fun EmotionFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (selected) MoodiaryCustomTheme.colors.indicationColor1 else MoodiaryCustomTheme.colors.indicationColor2,
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
            color = if (selected) MoodiaryCustomTheme.colors.fontColor5 else MoodiaryCustomTheme.colors.fontColor1,
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
            style = MoodiaryCustomTheme.typography.bodySmall
        )
    }
}