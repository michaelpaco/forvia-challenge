package com.wazowski.forviachallenge.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.PADDING_L
import com.wazowski.forviachallenge.presentation.*
import com.wazowski.forviachallenge.presentation.composables.*
import com.wazowski.forviachallenge.presentation.error.ErrorMessage
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    uiState: State<HomeUiState>, onCardClick: (Int) -> Unit, onSeeMoreClick: () -> Unit
) {
    Scaffold {
        Column {
            when (val state = uiState.value) {
                is HomeUiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is HomeUiState.Success -> {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                        AppListRowWithBackground(
                            apps = state.topDownloadedApps,
                        ) { app ->
                            AppListElevatedCard(app = app, onClick = onCardClick)
                        }

                        AppListRow(
                            apps = state.latestAddedApps,
                            title = stringResource(id = R.string.list_latest_added),
                            modifier = Modifier.padding(top = PADDING_L.dp),
                        ) { app ->
                            AppListRowCardItem(app = app, onClick = onCardClick)
                        }

                        AppListRow(
                            apps = state.allApps,
                            title = stringResource(id = R.string.list_all),
                            modifier = Modifier.padding(vertical = PADDING_L.dp),
                            onSeeMoreApps = onSeeMoreClick
                        ) { app ->
                            AppListRowCardItem(app = app, onClick = onCardClick)
                        }
                    }
                }

                is HomeUiState.Empty -> {
                    ErrorMessage(message = stringResource(R.string.error_no_content))
                }

                is HomeUiState.Error -> {
                    ErrorMessage(message = stringResource(R.string.error_no_connection))
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
            uiState = uiState,
            onCardClick = {}
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
            uiState = uiState,
            onCardClick = {}
        ) {}
    }
}