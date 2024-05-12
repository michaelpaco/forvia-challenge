package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.PADDING_XL
import com.wazowski.forviachallenge.common.Constants.PADDING_XXL
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.*

@Composable
fun AppListRowWithBackground(
    apps: List<ForviaApp>, content: @Composable (ForviaApp) -> Unit
) {
    Box(
        modifier = Modifier
            .height(340.dp)
            .background(
                LocalCustomPalette.current.listBackground,
                shape = RoundedCornerShape(0, 0, 0, 8)
            )
    ) {
        Text(
            text = stringResource(R.string.list_highlights_message),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(bottom = PADDING_XXL.dp.multiply(2.8f), start = PADDING_XL.dp)
                .fillMaxWidth(.8f),
            letterSpacing = 2.sp,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold

        )

        AppListRow(
            apps = apps, modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    vertical = PADDING_XL.dp
                ), title = stringResource(id = R.string.list_popular)
        ) { app ->
            content(app)
        }
    }
}

@Preview
@Composable
fun AppListWIthHeroPreview() {
    ForviaChallengeTheme {
        Surface {
            AppListRowWithBackground(allApps) { app ->
                AppListElevatedCard(app, onClick = {})
            }
        }
    }
}