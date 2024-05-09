package com.wazowski.forviachallenge.di

import android.content.Context
import androidx.room.Room
import com.wazowski.forviachallenge.data.local.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ForviaDatabase {
        return Room.databaseBuilder(
            appContext,
            ForviaDatabase::class.java,
            "ForviaDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(forviaDatabase: ForviaDatabase): ForviaAppDao {
        return forviaDatabase.forviaAppDao()
    }
}