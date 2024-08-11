package com.lkw1120.client.repository

import com.lkw1120.client.datasource.RemoteDataSource
import com.lkw1120.client.datasource.remote.response.RepoListResp
import javax.inject.Inject

interface ReposRepository {

    suspend fun getRepoList(userName: String, page: Int): RepoListResp

}

class ReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): ReposRepository {

    override suspend fun getRepoList(userName: String, page: Int): RepoListResp {
        return remoteDataSource.getRepoList(userName,page)
    }
}
