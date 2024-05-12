package com.wazowski.forviachallenge.presentation.seemore

import com.wazowski.forviachallenge.domain.model.ForviaApp

sealed class SeeMoreUiState {

    data object Empty : SeeMoreUiState()
    data object Loading : SeeMoreUiState()
    data class Error(val message: String) : SeeMoreUiState()
    data class Success(
        val allApps: List<ForviaApp> = emptyList()
    ) : SeeMoreUiState()
}