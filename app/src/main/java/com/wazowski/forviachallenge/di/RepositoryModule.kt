package com.wazowski.forviachallenge.di

import com.wazowski.forviachallenge.data.repository.*
import com.wazowski.forviachallenge.domain.repository.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindForviaApiRepository(
        forviaApiRepositoryImpl: ForviaApiRepositoryImpl
    ): ForviaApiRepository

    @Binds
    @Singleton
    abstract fun bindForviaLocalRepository(
        forviaLocalRepositoryImpl: ForviaLocalRepositoryImpl
    ): ForviaLocalRepository

    @Binds
    @Singleton
    abstract fun bindConnectivityRepository(
        connectivityRepositoryImpl: ConnectivityRepositoryImpl
    ): ConnectivityRepository
}