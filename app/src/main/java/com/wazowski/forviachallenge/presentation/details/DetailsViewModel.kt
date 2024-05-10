package com.wazowski.forviachallenge.presentation.details

import androidx.lifecycle.*
import com.wazowski.forviachallenge.common.Resource
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
    val uiState = _uiState.asStateFlow()

    suspend fun getAppById(appId: Int) = withContext(Dispatchers.IO) {
        when (val result = forviaLocalRepository.getAppById(appId = appId)) {
            is Resource.Success -> {
                result.data?.let { app ->
                    _uiState.value = DetailsUiState.Success(app = app)
                }
            }
            is Resource.Error -> {
                _uiState.value = DetailsUiState.Error("There was an error, try again.")
            }
        }
    }
}