package com.wazowski.forviachallenge.presentation.seemore

import androidx.lifecycle.*
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.SEE_MORE_FETCH_ALL_LIMIT
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SeeMoreViewModel @Inject constructor(
    private val forviaLocalRepository: ForviaLocalRepository,
    private val forviaApiRepository: ForviaApiRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<SeeMoreUiState>(SeeMoreUiState.Empty)
    private val _allApps = MutableStateFlow<List<ForviaApp>?>(null)

    val uiState = combine(
        _uiState, _allApps
    ) { uiState, allApps ->
        when (uiState) {
            is SeeMoreUiState.Success -> {
                if (allApps == null) SeeMoreUiState.Loading
                else SeeMoreUiState.Success(allApps = allApps)
            }

            is SeeMoreUiState.Error -> {
                SeeMoreUiState.Error(message = uiState.message)
            }

            else -> {
                SeeMoreUiState.Loading
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SeeMoreUiState.Loading
    )

    init {
        collectAllApps()
    }

    private fun collectAllApps() {
        _uiState.value = SeeMoreUiState.Loading
        viewModelScope.launch {
            when (val result =
                forviaLocalRepository.getAllApps(limit = SEE_MORE_FETCH_ALL_LIMIT)) {
                is Resource.Success -> {
                    result.data?.collect { apps ->
                        _allApps.update { apps }
                        _uiState.value = SeeMoreUiState.Success(allApps = apps)
                    }
                }

                else -> {
                    result.message?.let {
                        _uiState.value = SeeMoreUiState.Error(message = it)
                    }
                }
            }
        }
    }

    fun fetchMoreAllApps() {
        viewModelScope.launch {
            when (val result = forviaApiRepository.getAllApps(
                limit = SEE_MORE_FETCH_ALL_LIMIT
            )) {
                is Resource.Success -> {
                    result.data?.let { apps ->
                        forviaLocalRepository.insertAll(apps)
                        _uiState.value = SeeMoreUiState.Success(allApps = apps)
                    }
                }

                is Resource.Error -> {
                    result.message?.let {
                        _uiState.value = SeeMoreUiState.Error(message = it)
                    }
                }
            }
        }
    }
}