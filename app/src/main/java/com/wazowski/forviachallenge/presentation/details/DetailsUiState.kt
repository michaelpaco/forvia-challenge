package com.wazowski.forviachallenge.presentation.details

import com.wazowski.forviachallenge.domain.model.ForviaApp

sealed class DetailsUiState {

    data object Empty : DetailsUiState()
    data object Loading : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
    data class Success(
        val app: ForviaApp,
        val relatedApps: List<ForviaApp> = emptyList()
    ) : DetailsUiState()
}