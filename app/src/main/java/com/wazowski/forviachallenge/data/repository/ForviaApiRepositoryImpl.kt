package com.wazowski.forviachallenge.data.repository

import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.data.mappers.toForviaAppList
import com.wazowski.forviachallenge.data.remote.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.ForviaApiRepository
import javax.inject.Inject

class ForviaApiRepositoryImpl @Inject constructor(
    private val api: ForviaApi
) : ForviaApiRepository {
    override suspend fun getAllApps(offset: Int, limit: Int): Resource<List<ForviaApp>> {
        return try {
            val appList = api.getAppsList(
                offset = offset,
                limit = limit
            ).datasets.toForviaAppList()
            Resource.Success(
                data = appList
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}