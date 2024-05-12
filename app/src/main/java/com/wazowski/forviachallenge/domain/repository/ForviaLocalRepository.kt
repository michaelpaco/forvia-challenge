package com.wazowski.forviachallenge.domain.repository

import com.wazowski.forviachallenge.common.Constants.FETCH_ALL_LIMIT
import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.domain.model.ForviaApp
import kotlinx.coroutines.flow.Flow

interface ForviaLocalRepository {
    suspend fun getAllApps(limit: Int = FETCH_ALL_LIMIT): Resource<Flow<List<ForviaApp>>>
    fun getAppById(appId: Int): Resource<ForviaApp>
    suspend fun insertAll(list: List<ForviaApp>): Resource<Unit>
    suspend fun deleteAll(): Resource<Unit>
    suspend fun getMostDownloadedApps(): Resource<Flow<List<ForviaApp>>>
    suspend fun getLatestAddedApps(): Resource<Flow<List<ForviaApp>>>
    suspend fun getAppsBySimilarRating(rating: Float): Resource<Flow<List<ForviaApp>>>
}