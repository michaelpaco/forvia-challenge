package com.wazowski.forviachallenge.presentation

import com.wazowski.forviachallenge.domain.model.ForviaApp

sealed class UiState {
    data object Empty : UiState()
    data object Loading : UiState()
    data class Error(val message: String) : UiState()
    data class Success(
        val allApps: List<ForviaApp> = emptyList(),
        val topDownloadedApps: List<ForviaApp> = emptyList(),
        val latestAddedApps: List<ForviaApp> = emptyList()
    ) : UiState()
}