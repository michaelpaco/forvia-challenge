package com.wazowski.forviachallenge.presentation

import android.util.Log
import androidx.lifecycle.*
import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.data.local.ForviaDatabase
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ForviaRepository,
    private val db: ForviaDatabase,
    connectivityRepository: ConnectivityRepository
) : ViewModel() {
    private val _appsList = MutableStateFlow<List<ForviaApp>>(emptyList())
    val appsList = _appsList.asStateFlow()

    val isConnected = connectivityRepository.isConnected

    init {
        viewModelScope.launch {
            repository.getDbAppsList().data?.collect {
                _appsList.value = it
            }
        }
    }

    fun getAppsList(isInDebugMode: Boolean = false) {
        viewModelScope.launch {
            when (val result = repository.getAppsList(
                offset = if (isInDebugMode) Random.nextInt(
                    0, 60001
                ) else 0
            )) {
                is Resource.Success -> {
                    result.data?.let { list ->
                        repository.insertAll(list)
                    }
                }

                is Resource.Error -> {
                    Log.e("MainViewModel", result.message!!.toString())
                }
            }
        }
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        db.runInTransaction {
            runBlocking {
                db.clearAllTables()
            }
        }
    }
}