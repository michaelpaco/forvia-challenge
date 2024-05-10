package com.wazowski.forviachallenge.domain.repository

import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.domain.model.ForviaApp

interface ForviaApiRepository {
    suspend fun getAllApps(offset: Int = 0): Resource<List<ForviaApp>>
}