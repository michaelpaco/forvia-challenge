package com.wazowski.forviachallenge.di

import android.content.Context
import com.wazowski.forviachallenge.data.remote.ForviaApi
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideForviaApi(): ForviaApi {
        return Retrofit.Builder()
            .baseUrl("https://ws2.aptoide.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
 }