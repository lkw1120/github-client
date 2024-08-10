package com.lkw1120.client.di

import com.lkw1120.client.datasource.RemoteDataSource
import com.lkw1120.client.datasource.RemoteDataSourceImpl
import com.lkw1120.client.datasource.remote.ApiConnection
import com.lkw1120.client.datasource.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(
    ): ApiService = ApiConnection.getService()

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            apiService = apiService
        )
    }
}