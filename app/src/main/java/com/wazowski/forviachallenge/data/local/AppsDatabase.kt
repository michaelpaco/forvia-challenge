package com.wazowski.forviachallenge.data.local

import androidx.room.*
import com.wazowski.forviachallenge.domain.model.ForviaApp

@Database(entities = [ForviaApp::class], version = 1)
abstract class ForviaDatabase : RoomDatabase() {
    abstract fun forviaAppDao(): ForviaAppDao
}