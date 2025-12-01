package com.example.moodiary.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

val lightColors = MoodiaryColors(
    background = Color(0xFFf8f7fe),
    fontColor1 = Color(0xFF1E1E1E),
    fontColor2 = Color(0xFF7C6FF6),
    fontColor3 = Color(0xFF8E8E8E),
    fontColor4 = Color(0xFF95A5A6),
    fontColor5 = Color(0xFFFFFFFF),
    fontColor6 = Color(0xFF1E1E1E),
    cardColor = Color(0xFFEFF4FB),
    buttonColor = Color(0xFF5E73AA),
    goodEmotionColor = Color(0xFF4CAF50),
    soSoEmotionColor = Color(0xFFFFC107),
    warningEmotionColor = Color(0xFFFF9800),
    badEmotionColor = Color(0xFFF44336),
    activeStrokeColor = Color(0xFF6C5CE7),
    inactiveStrokeColor = Color(0xFF95A5A6),
    iconColor1 = Color(0xFFFFFFFF),
    iconColor2 = Color(0xFFE2E2EC),
    iconColor3 = Color(0xFF67CFA3),
    indicationColor1 = Color(0xFF62697C),
    indicationColor2 = Color(0xFFF0F0F0),
    onThumbColor = Color(0xFF7B61FF),
    onTrackColor = Color(0xFFE6E1FF),
    offThumbColor = Color(0xFFC6C6C6),
    offTrackColor = Color(0xFFE5E5E5),
    borderColor1 = Color(0xFF95A5A6),
    transparent = Color(0x00000000),
    errorColor1 = Color(0xFFB3261E)
)

val darkColors = MoodiaryColors(
    background = Color(0xFF2B2B2B),
    fontColor1 = Color(0xFF1E1E1E),
    fontColor2 = Color(0xFF6C5CE7),
    fontColor3 = Color(0xFF8E8E8E),
    fontColor4 = Color(0xFF95A5A6),
    fontColor5 = Color(0xFFFFFFFF),
    fontColor6 = Color(0xFFFFFFFF),
    cardColor = Color(0xFFE2E2EC),
    buttonColor = Color(0xFF5E73AA),
    goodEmotionColor = Color(0xFF4CAF50),
    soSoEmotionColor = Color(0xFFFFC107),
    warningEmotionColor = Color(0xFFFF9800),
    badEmotionColor = Color(0xFFF44336),
    activeStrokeColor = Color(0xFF6C5CE7),
    inactiveStrokeColor = Color(0xFF95A5A6),
    iconColor1 = Color(0xFFFFFFFF),
    iconColor2 = Color(0xFFE2E2EC),
    iconColor3 = Color(0xFF67CFA3),
    indicationColor1 = Color(0xFF62697C),
    indicationColor2 = Color(0xFFF0F0F0),
    onThumbColor = Color(0xFF7B61FF),
    onTrackColor = Color(0xFFE6E1FF),
    offThumbColor = Color(0xFFC6C6C6),
    offTrackColor = Color(0xFFE5E5E5),
    borderColor1 = Color(0xFF95A5A6),
    transparent = Color(0x00000000),
    errorColor1 = Color(0xFFB3261E)
)

class MoodiaryColors(
    background: Color,
    fontColor1: Color,
    fontColor2: Color,
    fontColor3: Color,
    fontColor4: Color,
    fontColor5: Color,
    fontColor6: Color,
    cardColor: Color,
    buttonColor: Color,
    goodEmotionColor: Color,
    soSoEmotionColor: Color,
    warningEmotionColor: Color,
    badEmotionColor: Color,
    activeStrokeColor: Color,
    inactiveStrokeColor: Color,
    iconColor1: Color,
    iconColor2: Color,
    iconColor3: Color,
    indicationColor1: Color,
    indicationColor2: Color,
    onThumbColor: Color,
    onTrackColor: Color,
    offThumbColor: Color,
    offTrackColor: Color,
    borderColor1: Color,
    transparent: Color,
    errorColor1: Color
) {
    var background by mutableStateOf(background)
        private set

    var fontColor1 by mutableStateOf(fontColor1)
        private set

    var fontColor2 by mutableStateOf(fontColor2)
        private set

    var fontColor3 by mutableStateOf(fontColor3)
        private set

    var fontColor4 by mutableStateOf(fontColor4)
        private set

    var fontColor5 by mutableStateOf(fontColor5)
        private set

    var fontColor6 by mutableStateOf(fontColor6)
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

    var iconColor1 by mutableStateOf(iconColor1)
        private set

    var iconColor2 by mutableStateOf(iconColor2)
        private set

    var iconColor3 by mutableStateOf(iconColor3)
        private set

    var indicationColor1 by mutableStateOf(indicationColor1)
        private set

    var indicationColor2 by mutableStateOf(indicationColor2)
        private set

    var onThumbColor by mutableStateOf(onThumbColor)
        private set

    var onTrackColor by mutableStateOf(onTrackColor)
        private set

    var offThumbColor by mutableStateOf(offThumbColor)
        private set

    var offTrackColor by mutableStateOf(offTrackColor)
        private set

    var borderColor1 by mutableStateOf(borderColor1)
        private set

    var transparent by mutableStateOf(transparent)
        private set

    var errorColor1 by mutableStateOf(errorColor1)
        private set

    fun copy(
        background: Color = this.background,
        fontColor1: Color = this.fontColor1,
        fontColor2: Color = this.fontColor2,
        fontColor3: Color = this.fontColor3,
        fontColor4: Color = this.fontColor4,
        fontColor5: Color = this.fontColor5,
        fontColor6: Color = this.fontColor6,
        cardColor: Color = this.cardColor,
        buttonColor: Color = this.buttonColor,
        goodEmotionColor: Color = this.goodEmotionColor,
        soSoEmotionColor: Color = this.soSoEmotionColor,
        warningEmotionColor: Color = this.warningEmotionColor,
        badEmotionColor: Color = this.badEmotionColor,
        activeStrokeColor: Color = this.activeStrokeColor,
        inactiveStrokeColor: Color = this.inactiveStrokeColor,
        iconColor1: Color = this.iconColor1,
        iconColor2: Color = this.iconColor2,
        iconColor3: Color = this.iconColor3,
        indicationColor1: Color = this.indicationColor1,
        indicationColor2: Color = this.indicationColor2,
        onThumbColor: Color = this.onThumbColor,
        onTrackColor: Color = this.onTrackColor,
        offThumbColor: Color = this.offThumbColor,
        offTrackColor: Color = this.offTrackColor,
        borderColor1: Color = this.borderColor1,
        transparent: Color = this.transparent,
        errorColor1: Color = this.errorColor1
    ) = MoodiaryColors(
        background = background,
        fontColor1 = fontColor1,
        fontColor2 = fontColor2,
        fontColor3 = fontColor3,
        fontColor4 = fontColor4,
        fontColor5 = fontColor5,
        fontColor6 = fontColor6,
        cardColor = cardColor,
        buttonColor = buttonColor,
        goodEmotionColor = goodEmotionColor,
        soSoEmotionColor = soSoEmotionColor,
        warningEmotionColor = warningEmotionColor,
        badEmotionColor = badEmotionColor,
        activeStrokeColor = activeStrokeColor,
        inactiveStrokeColor = inactiveStrokeColor,
        iconColor1 = iconColor1,
        iconColor2 = iconColor2,
        iconColor3 = iconColor3,
        indicationColor1 = indicationColor1,
        indicationColor2 = indicationColor2,
        onThumbColor = onThumbColor,
        onTrackColor = onTrackColor,
        offThumbColor = offThumbColor,
        offTrackColor = offTrackColor,
        borderColor1 = borderColor1,
        transparent = transparent,
        errorColor1 = errorColor1
    )

    fun updateColorsFrom(other: MoodiaryColors) {
        background = other.background
        fontColor1 = other.fontColor1
        fontColor2 = other.fontColor2
        fontColor3 = other.fontColor3
        fontColor4 = other.fontColor4
        fontColor5 = other.fontColor5
        fontColor6 = other.fontColor6
        cardColor = other.cardColor
        buttonColor = other.buttonColor
        goodEmotionColor = other.goodEmotionColor
        soSoEmotionColor = other.soSoEmotionColor
        warningEmotionColor = other.warningEmotionColor
        badEmotionColor = other.badEmotionColor
        activeStrokeColor = other.activeStrokeColor
        inactiveStrokeColor = other.inactiveStrokeColor
        iconColor1 = other.iconColor1
        iconColor2 = other.iconColor2
        iconColor3 = other.iconColor3
        indicationColor1 = other.indicationColor1
        indicationColor2 = other.indicationColor2
        onThumbColor = other.onThumbColor
        onTrackColor = other.onTrackColor
        offThumbColor = other.offThumbColor
        offTrackColor = other.offTrackColor
        borderColor1 = other.borderColor1
        transparent = other.transparent
        errorColor1 = other.errorColor1
    }
}