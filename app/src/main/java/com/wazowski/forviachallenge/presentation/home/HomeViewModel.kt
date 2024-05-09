package com.wazowski.forviachallenge.presentation.home

import androidx.lifecycle.ViewModel
import com.wazowski.forviachallenge.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*

@HiltViewModel
class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState


}