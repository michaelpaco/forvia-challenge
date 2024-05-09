package com.wazowski.forviachallenge.presentation

import com.wazowski.forviachallenge.domain.model.ForviaApp

sealed class UiState {
    data object Loading : UiState()
    data class Success(
        val allApps: List<ForviaApp>
    ) : UiState()

    data class Error(val message: String) : UiState()
}