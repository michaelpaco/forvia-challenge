package com.wazowski.forviachallenge.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.Constants.PADDING_XL
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
@Composable
fun AppListRow(
    apps: List<ForviaApp>,
    modifier: Modifier = Modifier,
    title: String = "",
    onFirstVisibleItemChange: ((ForviaApp) -> Unit)? = null,
    onSeeMoreApps: (() -> Unit)? = null,
    content: @Composable (ForviaApp) -> Unit
) {
    val isInPopularList = title == "Popular"
    val scrollState = rememberLazyListState()
    var visibleItemIndex: Int

    LaunchedEffect(key1 = scrollState) {
        if (onFirstVisibleItemChange == null) return@LaunchedEffect

        snapshotFlow {
            scrollState.layoutInfo
        }.debounce(500L).collectLatest { layoutInfo ->

            visibleItemIndex = if (layoutInfo.visibleItemsInfo.size > 2) {
                layoutInfo.visibleItemsInfo[1].index
            } else {
                layoutInfo.visibleItemsInfo[0].index
            }

            onFirstVisibleItemChange.invoke(apps[visibleItemIndex])
        }
    }

    Column(
        modifier = modifier
    ) {
        if (title.isNotEmpty()) Row(
            modifier = Modifier.padding(
                start = PADDING_XL.dp, bottom = PADDING_M.dp
            )
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = if (isInPopularList) Color.White else Color.Black
            )
        }
        LazyRow(
            state = scrollState,
            contentPadding = PaddingValues(horizontal = PADDING_M.dp, vertical = PADDING_XS.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = apps.size) {
                content(apps[it])
            }

            item {
                IconButton(onClick = { onSeeMoreApps?.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "More"
                    )
                }
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