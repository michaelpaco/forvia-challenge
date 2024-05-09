package com.wazowski.forviachallenge.data.repository

import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.data.local.ForviaAppDao
import com.wazowski.forviachallenge.data.mappers.toForviaAppList
import com.wazowski.forviachallenge.data.remote.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.ForviaRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ForviaRepositoryImpl @Inject constructor(
    private val api: ForviaApi,
    private val forviaAppDao: ForviaAppDao
) : ForviaRepository {

    override suspend fun getAppsList(offset: Int): Resource<List<ForviaApp>> {
        return try {
            val appList = api.getAppsList(
                offset = offset
            ).datasets.toForviaAppList()
            Resource.Success(
                data = appList
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getDbAppsList(): Resource<Flow<List<ForviaApp>>> {
        return try {
            Resource.Success(
                data = forviaAppDao.getAll()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun insertAll(list: List<ForviaApp>): Resource<Unit> {
        return try {
            val result = forviaAppDao.insertAll(list)

            Resource.Success(
                data = result,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
    override suspend fun deleteAll(): Resource<Unit> {
        return try {
            forviaAppDao.deleteAll()
            Resource.Success()
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}