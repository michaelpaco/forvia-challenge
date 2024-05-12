package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(y = 32.dp)
            .background(
                LocalCustomPalette.current.listBackground,
                shape = RoundedCornerShape(0, 8, 0, 0)
            )
    ) {
        Text(
            text = "Apps with similar rating",
            color = Color.White,
            modifier = Modifier
                .padding(top = PADDING_M.dp, start = PADDING_XL.dp)
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
                .fillMaxHeight()
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