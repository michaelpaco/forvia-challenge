package com.wazowski.forviachallenge.di

import com.wazowski.forviachallenge.data.repository.ForviaRepositoryImpl
import com.wazowski.forviachallenge.domain.repository.ForviaRepository
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindForviaRepository(
        forviaRepositoryImpl: ForviaRepositoryImpl
    ): ForviaRepository
}