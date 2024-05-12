package com.wazowski.forviachallenge.domain.repository

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface ConnectivityRepository {
    val isConnected: StateFlow<Boolean>
}

class ConnectivityRepositoryImpl @Inject constructor(context: Context) : ConnectivityRepository {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val _isConnected = MutableStateFlow(false)

    override val isConnected = _isConnected.asStateFlow()

    init {
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                _isConnected.value = true
            }

            override fun onLost(network: android.net.Network) {
                _isConnected.value = false
            }
        })
    }
}