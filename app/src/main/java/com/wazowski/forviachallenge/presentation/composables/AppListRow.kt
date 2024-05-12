package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.ALL_APPS_TITLE
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.Constants.PADDING_XL
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.common.Constants.POPULAR_APPS_TITLE
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun AppListRow(
    apps: List<ForviaApp>,
    modifier: Modifier = Modifier,
    title: String = "",
    onSeeMoreApps: (() -> Unit)? = null,
    content: @Composable (ForviaApp) -> Unit
) {
    val hasBackground = title == stringResource(id = POPULAR_APPS_TITLE) || title == ""
    val isAllAppsList = title == stringResource(id = ALL_APPS_TITLE)

    Column(
        modifier = modifier
    ) {
        if (title.isNotEmpty()) Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(
                    start = PADDING_XL.dp, bottom = PADDING_M.dp
                )
                .fillMaxWidth()
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = if (hasBackground) Color.White else MaterialTheme.colorScheme.onSurface
            )

            if (isAllAppsList) {
                IconButton(onClick = { onSeeMoreApps?.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = Icons.AutoMirrored.Filled.ArrowForwardIos.name,
                        tint = if (hasBackground) Color.White else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = PADDING_M.dp, vertical = PADDING_XS.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = apps.size) {
                content(apps[it])
            }
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