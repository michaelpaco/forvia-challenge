package com.wazowski.forviachallenge.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.allApps
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun AppListRow(
    apps: List<ForviaApp>, content: @Composable (ForviaApp) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(PADDING_M.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(count = apps.size) {
            content(apps[it])
        }

        item {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "More"
            )
        }
    }
}

@Preview
@Composable
fun AppListRowPreview() {
    ForviaChallengeTheme {
        Surface {
            AppListRow(apps = allApps) {
                AppListRowCardItem(app = it, onClick = {})
            }
        }
    }
}