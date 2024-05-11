package com.wazowski.forviachallenge.data.local

import androidx.room.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import kotlinx.coroutines.flow.Flow

@Dao
interface ForviaAppDao {
    @Query("SELECT * FROM apps")
    fun getAll(): Flow<List<ForviaApp>>

    @Query("SELECT * FROM apps where id = :appId")
    fun getById(appId: Int): ForviaApp

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apps: List<ForviaApp>)

    @Query("DELETE FROM apps")
    suspend fun deleteAll()

    @Query("SELECT * FROM apps ORDER BY downloads DESC LIMIT 10")
    fun getMostDownloadedApps(): Flow<List<ForviaApp>>

    @Query("SELECT * FROM apps ORDER BY added DESC LIMIT 10")
    fun getLatestAddedApps(): Flow<List<ForviaApp>>

    @Query(
        """
        SELECT 
            * 
        FROM apps 
        WHERE ABS(rating - :givenRating) <= :threshold
        ORDER BY ABS(rating - :givenRating) ASC
        LIMIT 3
        """
    )
    fun getAppsBySimilarRating(givenRating: Float, threshold: Float = 0.5f): Flow<List<ForviaApp>>
}