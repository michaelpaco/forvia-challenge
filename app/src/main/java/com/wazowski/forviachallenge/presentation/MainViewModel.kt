package com.wazowski.forviachallenge.presentation

import android.util.Log
import androidx.lifecycle.*
import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.ForviaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ForviaRepository
) : ViewModel() {
    val appsList = MutableStateFlow<List<ForviaApp>>(emptyList())

    fun getAppsList() {
        viewModelScope.launch {
            // TODO: Remove this after finishing implementation
            when (val result = repository.getAppsList(offset = Random.nextInt(0, 60001))) {
                is Resource.Success -> {
                    result.data?.let {
                        appsList.value = result.data
                    }
                }

                is Resource.Error -> {
                    Log.e("MainViewModel", result.message!!.toString())
                }
            }
        }
    }
}