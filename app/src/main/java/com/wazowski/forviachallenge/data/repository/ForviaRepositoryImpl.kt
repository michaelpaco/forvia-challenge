package com.wazowski.forviachallenge.data.repository

import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.data.mappers.toForviaAppList
import com.wazowski.forviachallenge.data.remote.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.ForviaRepository
import javax.inject.Inject

class ForviaRepositoryImpl @Inject constructor(
    private val api: ForviaApi
) : ForviaRepository {

    override suspend fun getAppsList(offset: Int): Resource<List<ForviaApp>> {
        return try {
            Resource.Success(
                data = api.getAppsList(
                    offset = offset
                ).datasets.toForviaAppList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}