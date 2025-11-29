package com.example.moodiary.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

val lightColors = MoodiaryColors(
    background = Color(0x99EAF3FF),
    fontColor = Color(0xFF1E1E1E),
    fontColor2 = Color(0xFF6C5CE7),
    fontColor3 = Color(0xFF8E8E8E),
    cardColor = Color(0xFFE2E2EC),
    buttonColor = Color(0xFF5E73AA),
    goodEmotionColor = Color(0xFF98FB98),
    soSoEmotionColor = Color(0x99FFD700),
    warningEmotionColor = Color(0xFFFF7F50),
    badEmotionColor = Color(0xFFDC143C),
    activeStrokeColor = Color(0xFF6C5CE7),
    inactiveStrokeColor = Color(0xFFC8D6EA)
)

val darkColors = MoodiaryColors(
    background = Color(0xFF2B2B2B),
    fontColor = Color(0xFFE2E2EC),
    fontColor2 = Color(0xFF6C5CE7),
    fontColor3 = Color(0xFF8E8E8E),
    cardColor = Color(0xFFE2E2EC),
    buttonColor = Color(0xFF5E73AA),
    goodEmotionColor = Color(0xFF98FB98),
    soSoEmotionColor = Color(0x99FFD700),
    warningEmotionColor = Color(0xFFFF7F50),
    badEmotionColor = Color(0xFFDC143C),
    activeStrokeColor = Color(0xFF6C5CE7),
    inactiveStrokeColor = Color(0xFFC8D6EA)
)

class MoodiaryColors(
    background: Color,
    fontColor: Color,
    fontColor2: Color,
    fontColor3: Color,
    cardColor: Color,
    buttonColor: Color,
    goodEmotionColor: Color,
    soSoEmotionColor: Color,
    warningEmotionColor: Color,
    badEmotionColor: Color,
    activeStrokeColor: Color,
    inactiveStrokeColor: Color
) {
    var background by mutableStateOf(background)
        private set

    var fontColor by mutableStateOf(fontColor)
        private set

    var fontColor2 by mutableStateOf(fontColor2)
        private set

    var fontColor3 by mutableStateOf(fontColor3)
        private set

    var cardColor by mutableStateOf(cardColor)
        private set

    var buttonColor by mutableStateOf(buttonColor)
        private set

    var goodEmotionColor by mutableStateOf(goodEmotionColor)
        private set

    var soSoEmotionColor by mutableStateOf(soSoEmotionColor)
        private set

    var warningEmotionColor by mutableStateOf(warningEmotionColor)
        private set

    var badEmotionColor by mutableStateOf(badEmotionColor)
        private set

    var activeStrokeColor by mutableStateOf(activeStrokeColor)
        private set

    var inactiveStrokeColor by mutableStateOf(inactiveStrokeColor)
        private set

    fun copy(
        background: Color = this.background,
        fontColor: Color = this.fontColor,
        fontColor2: Color = this.fontColor2,
        fontColor3: Color = this.fontColor3,
        cardColor: Color = this.cardColor,
        buttonColor: Color = this.buttonColor,
        goodEmotionColor: Color = this.goodEmotionColor,
        soSoEmotionColor: Color = this.soSoEmotionColor,
        warningEmotionColor: Color = this.warningEmotionColor,
        badEmotionColor: Color = this.badEmotionColor,
        activeStrokeColor: Color = this.activeStrokeColor,
        inactiveStrokeColor: Color = this.inactiveStrokeColor
    ) = MoodiaryColors(
        background = background,
        fontColor = fontColor,
        fontColor2 = fontColor2,
        fontColor3 = fontColor3,
        cardColor = cardColor,
        buttonColor = buttonColor,
        goodEmotionColor = goodEmotionColor,
        soSoEmotionColor = soSoEmotionColor,
        warningEmotionColor = warningEmotionColor,
        badEmotionColor = badEmotionColor,
        activeStrokeColor = activeStrokeColor,
        inactiveStrokeColor = inactiveStrokeColor
    )

    fun updateColorsFrom(other: MoodiaryColors) {
        background = other.background
        fontColor = other.fontColor
        fontColor2 = other.fontColor2
        fontColor3 = other.fontColor3
        cardColor = other.cardColor
        buttonColor = other.buttonColor
        goodEmotionColor = other.goodEmotionColor
        soSoEmotionColor = other.soSoEmotionColor
        warningEmotionColor = other.warningEmotionColor
        badEmotionColor = other.badEmotionColor
        activeStrokeColor = other.activeStrokeColor
        inactiveStrokeColor = other.inactiveStrokeColor
    }
}