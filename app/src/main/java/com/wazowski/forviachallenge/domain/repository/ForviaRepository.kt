package com.wazowski.forviachallenge.domain.repository

import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.domain.model.ForviaApp
import kotlinx.coroutines.flow.Flow

interface ForviaRepository {
    suspend fun getAppsList(offset: Int = 0): Resource<List<ForviaApp>>
    suspend fun getDbAppsList(): Resource<Flow<List<ForviaApp>>>
    suspend fun insertAll(list: List<ForviaApp>): Resource<Unit>
}