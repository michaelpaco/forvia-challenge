package com.wazowski.forviachallenge.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import com.wazowski.forviachallenge.common.allApps
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun HomeScreen(appList: List<ForviaApp>, onCardClick: (Int) -> Unit) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            AppList(apps = appList, onCardClick = { id -> onCardClick(id)})
        }
    }
}

@Composable
@PreviewLightDark
fun HomeScreenPreview() {
    ForviaChallengeTheme {
        HomeScreen(appList = allApps, onCardClick = {})
    }
}