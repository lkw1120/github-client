package com.lkw1120.client.di

import com.lkw1120.client.datasource.RemoteDataSource
import com.lkw1120.client.repository.ReposRepository
import com.lkw1120.client.repository.ReposRepositoryImpl
import com.lkw1120.client.repository.UsersRepository
import com.lkw1120.client.repository.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideReposRepository(
        remoteDataSource: RemoteDataSource
    ): ReposRepository = ReposRepositoryImpl(
        remoteDataSource = remoteDataSource
    )

    @Singleton
    @Provides
    fun provideUsersRepository(
        remoteDataSource: RemoteDataSource
    ): UsersRepository = UsersRepositoryImpl(
        remoteDataSource = remoteDataSource
    )
}