package com.wazowski.forviachallenge.domain.repository

import com.wazowski.forviachallenge.common.Constants.FETCH_ALL_LIMIT
import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.domain.model.ForviaApp

interface ForviaApiRepository {
    suspend fun getAllApps(offset: Int = 0, limit: Int = FETCH_ALL_LIMIT): Resource<List<ForviaApp>>
}