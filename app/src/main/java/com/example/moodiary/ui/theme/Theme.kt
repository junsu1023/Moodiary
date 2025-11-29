package com.example.moodiary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

object MoodiaryCustomTheme {
    val colors: MoodiaryColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: androidx.compose.material3.Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

val LocalColors = staticCompositionLocalOf { lightColors }
val LocalTypography = staticCompositionLocalOf { Typography }

@Composable
fun MoodiaryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val currentColor = remember(darkTheme) { if(darkTheme) darkColors else lightColors  }
    val rememberedColors = remember(darkTheme) { currentColor.copy() }.apply { updateColorsFrom(currentColor) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides Typography
    ) {
        content()
    }
}