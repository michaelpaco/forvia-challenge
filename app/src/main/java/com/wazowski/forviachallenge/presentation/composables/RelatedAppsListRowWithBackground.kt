package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.Constants.PADDING_XL
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.*

@Composable
fun RelatedAppsListRowWithBackground(
    apps: List<ForviaApp>, content: @Composable (ForviaApp) -> Unit
) {
    val listBackgroundColor = LocalCustomPalette.current.listBackground
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(y = 32.dp)
            .background(
                color = listBackgroundColor
            )
            .drawWithContent {
                val cornerRadius = CornerRadius(50f, 50f)
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(0f, -115f),
                                size = Size(size.width, size.height),
                            ),
                            topRight = cornerRadius,
                        )
                    )
                }
                drawPath(path, color = listBackgroundColor)
                drawContent()
            }
    ) {
        Text(
            text = "Apps with similar rating",
            color = Color.White,
            modifier = Modifier
                .padding(start = PADDING_XL.dp)
                .fillMaxWidth(.8f),
            letterSpacing = 2.sp,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        AppListRow(
            apps = apps, modifier = Modifier
                .padding(
                    top = PADDING_M.dp,
                )
        ) { app ->
            content(app)
        }
    }
}

@Preview
@Composable
fun RelatedAppsListRowWithBackgroundPreview() {
    ForviaChallengeTheme {
        Surface(modifier = Modifier.height(250.dp)) {
            RelatedAppsListRowWithBackground(allApps) { app ->
                RelatedAppElevatedCard(app, onClick = {})
            }
        }
    }
}