package com.lkw1120.client.datasource

import com.lkw1120.client.datasource.remote.ApiService
import com.lkw1120.client.datasource.remote.response.RepoListResp
import com.lkw1120.client.datasource.remote.response.UserDetailResp
import com.lkw1120.client.datasource.remote.response.UserListResp
import timber.log.Timber
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getUserList(query: String, page: Int): UserListResp

    suspend fun getUserDetail(userName: String): UserDetailResp

    suspend fun getRepoList(userName: String, page: Int): RepoListResp

}
class RemoteDataSourceImpl @Inject constructor(
    private  val apiService: ApiService
): RemoteDataSource {

    override suspend fun getUserList(query: String, page: Int): UserListResp {
        val response = apiService.getUserList(query,page)
        when(response.code()) {
            HTTP_OK -> {
                return response.body()?:
                throw Exception(response.errorBody()?.string())
            }
            else -> {
                throw Exception(response.errorBody()?.string())
            }
        }
    }

    override suspend fun getUserDetail(userName: String): UserDetailResp {
        val response = apiService.getUserDetail(userName)
        when(response.code()) {
            HTTP_OK -> {
                return response.body()?:
                throw Exception(response.errorBody()?.string())
            }
            else -> {
                Timber.tag("error").d(response.errorBody()?.string())
                throw Exception(response.errorBody()?.string())
            }
        }
    }

    override suspend fun getRepoList(userName: String, page: Int): RepoListResp {
        val response = apiService.getRepoList(userName,page)
        when(response.code()) {
            HTTP_OK -> {
                return response.body()?:
                throw Exception(response.errorBody()?.string())
            }
            else -> {
                Timber.tag("error").d(response.errorBody()?.string())
                throw Exception(response.errorBody()?.string())
            }
        }
    }

}