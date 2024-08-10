package com.lkw1120.client.repository

import com.lkw1120.client.datasource.RemoteDataSource
import javax.inject.Inject

interface UsersRepository {

}

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): UsersRepository {

}