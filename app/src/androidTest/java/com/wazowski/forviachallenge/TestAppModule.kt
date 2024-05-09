package com.wazowski.forviachallenge

import android.content.Context
import androidx.room.Room
import com.wazowski.forviachallenge.data.local.ForviaDatabase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context, ForviaDatabase::class.java
    ).allowMainThreadQueries().build()
}