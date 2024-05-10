package com.wazowski.forviachallenge.presentation.home

import android.util.Log
import androidx.lifecycle.*
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.*
import com.wazowski.forviachallenge.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val forviaApiRepository: ForviaApiRepository,
    private val forviaLocalRepository: ForviaLocalRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    private val _allApps = MutableStateFlow<List<ForviaApp>?>(null)
    private val _topDownloadedApps = MutableStateFlow<List<ForviaApp>?>(null)

    val uiState =
        combine(_uiState, _allApps, _topDownloadedApps) { uiState, allApps, topDownloadedApps ->
            when (uiState) {
                is UiState.Success -> {
                    if (allApps == null || topDownloadedApps == null) UiState.Loading
                    else if (allApps.isEmpty()) UiState.Empty
                    else UiState.Success(allApps, topDownloadedApps)
                }

                is UiState.Error -> {
                    UiState.Error(message = uiState.message)
                }

                else -> {
                    Log.d("HomeViewModel", "UiState is $uiState")
                    UiState.Loading
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    init {
        collectAllApps()
        collectMostDownloadedApps()
    }

    private fun collectAllApps() {
        viewModelScope.launch {
            when (val result = forviaLocalRepository.getAllApps()) {
                is Resource.Success -> {
                    result.data?.collect { apps ->
                        _allApps.update { apps }
                    }
                }

                else -> {
                    result.message?.let {
                        _uiState.value = UiState.Error(message = it)
                    }
                }
            }
        }
    }

    private fun collectMostDownloadedApps() {
        viewModelScope.launch {
            when (val result = forviaLocalRepository.getMostDownloadedApps()) {
                is Resource.Success -> {
                    result.data?.collect { apps ->
                        _topDownloadedApps.update { apps }
                    }
                }

                else -> {
                    result.message?.let {
                        _uiState.value = UiState.Error(message = it)
                    }
                }
            }
        }
    }

    fun getAppsList(isInDebugMode: Boolean = false) {
        viewModelScope.launch {
            when (val result = forviaApiRepository.getAllApps(
                offset = if (isInDebugMode) Random.nextInt(
                    0, 60001
                ) else 0
            )) {
                is Resource.Success -> {
                    result.data?.let { apps ->
                        forviaLocalRepository.insertAll(apps)
                        _uiState.value = UiState.Success(allApps = apps)
                    }
                }

                is Resource.Error -> {
                    result.message?.let {
                        _uiState.value = UiState.Error(message = "No internet connection")
                    }
                }
            }
        }
    }
}