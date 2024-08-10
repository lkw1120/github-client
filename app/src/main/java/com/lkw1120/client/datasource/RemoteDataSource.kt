package com.lkw1120.client.datasource

import com.lkw1120.client.datasource.remote.ApiService
import com.lkw1120.client.datasource.remote.response.UserListResp
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getUserList(query: String, page: Int): UserListResp

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
}