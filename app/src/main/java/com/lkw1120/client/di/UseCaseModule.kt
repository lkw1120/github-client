package com.lkw1120.client.di

import com.lkw1120.client.domain.ReposUseCase
import com.lkw1120.client.domain.ReposUseCaseImpl
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.UsersUseCaseImpl
import com.lkw1120.client.repository.ReposRepository
import com.lkw1120.client.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUsersUseCase(
        usersRepository: UsersRepository
    ): UsersUseCase = UsersUseCaseImpl(
        usersRepository = usersRepository
    )

    @Singleton
    @Provides
    fun provideReposUseCase(
        reposRepository: ReposRepository
    ): ReposUseCase = ReposUseCaseImpl(
        reposRepository = reposRepository
    )
}