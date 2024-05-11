package com.wazowski.forviachallenge.data.repository

import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.data.local.ForviaAppDao
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.domain.repository.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ForviaLocalRepositoryImpl @Inject constructor(
    private val forviaAppDao: ForviaAppDao
) : ForviaLocalRepository {

    override suspend fun getAllApps(): Resource<Flow<List<ForviaApp>>> {
        return try {
            Resource.Success(
                data = forviaAppDao.getAll()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override fun getAppById(appId: Int): Resource<ForviaApp> {
        return try {
            Resource.Success(
                data = forviaAppDao.getById(appId)
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

    override suspend fun getMostDownloadedApps(): Resource<Flow<List<ForviaApp>>> {
        return try {
            Resource.Success(
                data = forviaAppDao.getMostDownloadedApps()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getLatestAddedApps(): Resource<Flow<List<ForviaApp>>> {
        return try {
            Resource.Success(
                data = forviaAppDao.getLatestAddedApps()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getAppsBySimilarRating(rating: Float): Resource<Flow<List<ForviaApp>>> {
        return try {
            Resource.Success(
                data = forviaAppDao.getAppsBySimilarRating(givenRating = rating)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}