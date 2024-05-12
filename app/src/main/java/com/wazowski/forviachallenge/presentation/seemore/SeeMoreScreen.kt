package com.wazowski.forviachallenge.presentation.seemore

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wazowski.forviachallenge.presentation.composables.*
import com.wazowski.forviachallenge.presentation.error.ErrorMessage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeeMoreScreen(
    uiState: State<SeeMoreUiState>,
    onCardClick: (Int) -> Unit,
    onBackPressed: () -> Unit,
    onFetchMoreApps: () -> Unit
) {
    Scaffold(topBar = {
        TopBar {
            onBackPressed()
        }
    }) {
        Column {
            when (val state = uiState.value) {
                is SeeMoreUiState.Success -> {
                    AppListGrid(apps = state.allApps, onCardClick = onCardClick)
                }

                is SeeMoreUiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is SeeMoreUiState.Empty -> {
                    ErrorMessage("No content to show")
                }

                is SeeMoreUiState.Error -> {
                    ErrorMessage("No internet connection")
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        onFetchMoreApps()
    }
}

@Preview
@Composable
fun SeeMoreScreenPreview() {

}

