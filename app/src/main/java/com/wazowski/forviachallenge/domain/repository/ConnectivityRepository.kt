package com.wazowski.forviachallenge.domain.repository

import kotlinx.coroutines.flow.*

interface ConnectivityRepository {
    val isConnected: StateFlow<Boolean>
}