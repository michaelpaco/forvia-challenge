package com.wazowski.forviachallenge.presentation.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomPalette(
    val extraColor1: Color = Color.Unspecified,
)

val LightExtraColor1 = Color(color = 0xFFFA5825)

val DarkExtraColor1 = Color(color = 0xFFFA5825)

val LightCustomPalette = CustomPalette(
    extraColor1 = LightExtraColor1
)

val DarkCustomPalette = CustomPalette(
    extraColor1 = DarkExtraColor1
)

val LocalCustomPalette = staticCompositionLocalOf { CustomPalette() }