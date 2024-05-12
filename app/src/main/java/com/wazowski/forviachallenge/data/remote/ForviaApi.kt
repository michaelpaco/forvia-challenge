package com.wazowski.forviachallenge.data.remote

import retrofit2.http.*

interface ForviaApi {

    @GET("/api/6/listApps")
    suspend fun getAppsList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : ForviaDatasetDto
}