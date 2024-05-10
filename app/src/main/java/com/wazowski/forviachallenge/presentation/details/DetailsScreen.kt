package com.wazowski.forviachallenge.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wazowski.forviachallenge.common.allApps
import com.wazowski.forviachallenge.presentation.*
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun DetailsScreen(uiState: State<DetailsUiState>, onBackPressed: () -> Unit) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Row {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
            when (val state = uiState.value) {
                is DetailsUiState.Success -> {
                    HeroImage(imageUrl = state.app.graphic)
                }

                else -> {}
            }
        }
    }
}


@Preview
@Composable
fun DetailsScreenDataPreview() {
    val uiState = remember {
        mutableStateOf(
            DetailsUiState.Success(app = allApps.first())
        )
    }

    ForviaChallengeTheme {
        DetailsScreen(uiState = uiState, {})
    }
}