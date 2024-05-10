package com.wazowski.forviachallenge.presentation.home

import com.wazowski.forviachallenge.domain.model.ForviaApp

sealed class HomeUiState {
    data object Empty : HomeUiState()
    data object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    data class Success(
        val allApps: List<ForviaApp> = emptyList(),
        val topDownloadedApps: List<ForviaApp> = emptyList(),
        val latestAddedApps: List<ForviaApp> = emptyList()
    ) : HomeUiState()
}