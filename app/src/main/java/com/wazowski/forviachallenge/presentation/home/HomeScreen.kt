package com.wazowski.forviachallenge.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.presentation.home.HomeUiState
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun HomeScreen(
    uiState: State<HomeUiState>, onCardClick: (Int) -> Unit,
) {
    Scaffold { _ ->
        Column(modifier = Modifier.statusBarsPadding()) {
            when (val state = uiState.value) {
                is HomeUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is HomeUiState.Success -> {
                    Column {
                        Text(
                            text = "Latest added apps",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        AppListRow(apps = state.latestAddedApps) { app ->
                            AppListRowCardItem(app = app, onClick = onCardClick)
                        }
                    }

                    HorizontalDivider()

                    Column {
                        Text(
                            text = "Top downloads",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        AppListRow(apps = state.topDownloadedApps) { app ->
                            AppListRowCardItem(app = app, onClick = onCardClick)
                        }
                    }

                    HorizontalDivider()

                    Column {
                        Text(
                            text = "All apps",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
                        )
                        AppListGrid(apps = state.allApps, onCardClick = { id -> onCardClick(id) })
                    }
                }

                is HomeUiState.Empty -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No content to show")
                    }
                }

                is HomeUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = state.message)
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
fun HomeScreenLoadingPreview() {
    val uiState = remember {
        mutableStateOf(
            HomeUiState.Loading
        )
    }

    ForviaChallengeTheme {
        HomeScreen(
            uiState = uiState
        ) {}
    }
}

@Composable
@PreviewLightDark
fun HomeScreenSuccessPreview() {
    val uiState = remember {
        mutableStateOf(
            HomeUiState.Success(allApps = allApps, topDownloadedApps = allApps)
        )
    }

    ForviaChallengeTheme {
        HomeScreen(
            uiState = uiState
        ) {}
    }
}