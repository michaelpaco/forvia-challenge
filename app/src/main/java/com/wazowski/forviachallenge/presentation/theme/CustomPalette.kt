package com.wazowski.forviachallenge.presentation.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomPalette(
    val listBackground: Color = Color.Unspecified,
    val buttonBackground: Color = Color.Unspecified,
    val metadataColor: Color = Color.Unspecified,
    val metadataBackgroundColor: Color = Color.Unspecified,
)

val ListBackgroundLight = Color(color = 0xFFFA5825)
val ButtonBackgroundLight = Color(color = 0xFF0C1932)
val MetadataColorLight = Color(color = 0xFFFA253D)
val MetadataBackgroundLight = Color(color = 0xFFFFC4B2)

val ListBackgroundDark = Color(color = 0xFFFA5825)
val ButtonBackgroundDark = Color(color = 0xFF0C1932)
val MetadataColorDark = Color(color = 0xFFFA253D)
val MetadataBackgroundDark = Color(color = 0xFFFFC4B2)

val LightCustomPalette = CustomPalette(
    listBackground = ListBackgroundLight,
    buttonBackground = ButtonBackgroundLight,
    metadataColor = MetadataColorLight,
    metadataBackgroundColor = MetadataBackgroundLight
)

val DarkCustomPalette = CustomPalette(
    listBackground = ListBackgroundDark,
    buttonBackground = ButtonBackgroundDark,
    metadataColor = MetadataColorDark,
    metadataBackgroundColor = MetadataBackgroundDark
)

val LocalCustomPalette = staticCompositionLocalOf { CustomPalette() }