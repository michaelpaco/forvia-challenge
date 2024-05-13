package com.wazowski.forviachallenge.presentation.details

import androidx.lifecycle.*
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val forviaLocalRepository: ForviaLocalRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Empty)
    private val _relatedApps = MutableStateFlow<List<ForviaApp>?>(null)
    private val _app = MutableStateFlow<ForviaApp?>(null)

    val uiState = combine(
        _uiState, _relatedApps, _app
    ) { uiState, relatedApps, app ->
        when (uiState) {
            is DetailsUiState.Success -> {
                if (relatedApps == null || app == null) DetailsUiState.Loading
                else DetailsUiState.Success(app = app, relatedApps = relatedApps)
            }

            is DetailsUiState.Error -> {
                DetailsUiState.Error(message = uiState.message)
            }

            else -> {
                DetailsUiState.Loading
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DetailsUiState.Loading
    )

    private fun collectRelatedApps(givenRating: Float) {
        viewModelScope.launch {
            when (val result = forviaLocalRepository.getAppsBySimilarRating(givenRating)) {
                is Resource.Success -> {
                    result.data?.collect { apps ->
                        _relatedApps.update {
                            apps.filter { app -> app.id != _app.value?.id }.shuffled().take(10)
                        }
                    }
                }

                else -> {
                    result.message?.let {
                        _uiState.value = DetailsUiState.Error(message = it)
                    }
                }
            }
        }
    }

    private suspend fun getAppById(appId: Int) = withContext(Dispatchers.IO) {
        _uiState.value = DetailsUiState.Loading
        when (val result = forviaLocalRepository.getAppById(appId = appId)) {
            is Resource.Success -> {
                result.data?.let { app ->
                    _app.update { app }
                    collectRelatedApps(givenRating = app.rating)
                    delay(800L)
                    _uiState.value = DetailsUiState.Success(app = app)

                }
            }

            is Resource.Error -> {
                _uiState.value = DetailsUiState.Error(message = result.message ?: "")
            }
        }
    }

    fun onNavigate(appId: Int) {
        if (appId == _app.value?.id) return

        _relatedApps.value = null
        _app.value = null

        viewModelScope.launch {
            getAppById(appId)
        }
    }
}