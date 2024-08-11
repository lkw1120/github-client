package com.lkw1120.client.repository

import com.lkw1120.client.datasource.RemoteDataSource
import com.lkw1120.client.datasource.remote.response.UserDetailResp
import com.lkw1120.client.datasource.remote.response.UserListResp
import javax.inject.Inject

interface UsersRepository {

    suspend fun getUserList(query: String, page: Int): UserListResp

    suspend fun getUserDetail(userName: String): UserDetailResp

}

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): UsersRepository {

    override suspend fun getUserList(query: String, page: Int): UserListResp {
        return remoteDataSource.getUserList(query,page)
    }

    override suspend fun getUserDetail(userName: String): UserDetailResp {
        return remoteDataSource.getUserDetail(userName)
    }
}