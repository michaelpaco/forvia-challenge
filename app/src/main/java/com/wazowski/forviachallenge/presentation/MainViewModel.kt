package com.wazowski.forviachallenge.presentation

import androidx.lifecycle.*
import com.wazowski.forviachallenge.data.local.ForviaDatabase
import com.wazowski.forviachallenge.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    connectivityRepository: ConnectivityRepository, private val db: ForviaDatabase
) : ViewModel() {
    val isConnected = connectivityRepository.isConnected

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        db.runInTransaction {
            runBlocking {
                db.clearAllTables()
            }
        }
    }
}