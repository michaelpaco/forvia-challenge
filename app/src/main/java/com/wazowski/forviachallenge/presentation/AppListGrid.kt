package com.wazowski.forviachallenge.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.allApps
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun AppList(apps: List<ForviaApp>, onCardClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(items = apps, key = { data -> data.id }) {
            AppListItem(app = it, onClick = onCardClick, modifier = Modifier.padding(6.dp))
        }
    }
}

@Composable
@Preview
fun AppListPreview() {
    ForviaChallengeTheme {
        Surface {
            AppList(apps = allApps, onCardClick = {})
        }
    }
}
