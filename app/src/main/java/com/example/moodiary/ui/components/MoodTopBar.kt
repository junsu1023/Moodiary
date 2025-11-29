package com.example.moodiary.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.moodiary.ui.theme.MoodiaryCustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodTopBar(title: String) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(start = 16.dp),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.CenterStart),
                    color = MoodiaryCustomTheme.colors.fontColor2
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MoodiaryCustomTheme.colors.background
        )
    )
}
