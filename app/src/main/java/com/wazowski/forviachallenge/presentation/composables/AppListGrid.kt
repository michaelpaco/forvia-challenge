package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.Constants.PADDING_S
import com.wazowski.forviachallenge.common.allApps
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun AppListGrid(apps: List<ForviaApp>, onCardClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(PADDING_M.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(items = apps, key = { data -> data.id }) {
            AppListGridItem(
                app = it,
                onClick = onCardClick,
                modifier = Modifier.padding(PADDING_S.dp)
            )
        }
    }
}

@Composable
@Preview
fun AppListPreview() {
    ForviaChallengeTheme {
        Surface {
            AppListGrid(apps = allApps, onCardClick = {})
        }
    }
}
