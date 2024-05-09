package com.wazowski.forviachallenge.data.local

import androidx.room.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import kotlinx.coroutines.flow.Flow

@Dao
interface ForviaAppDao {
    @Query("SELECT * FROM apps")
    fun getAll(): Flow<List<ForviaApp>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apps: List<ForviaApp>)
}