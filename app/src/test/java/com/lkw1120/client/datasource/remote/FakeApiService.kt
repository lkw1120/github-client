package com.lkw1120.client.datasource.remote

import com.lkw1120.client.datasource.remote.response.FakeResp
import com.lkw1120.client.datasource.remote.response.RepoListResp
import com.lkw1120.client.datasource.remote.response.UserDetailResp
import com.lkw1120.client.datasource.remote.response.UserListResp
import retrofit2.Response

class FakeApiService: ApiService {

    override suspend fun getUserDetail(
        userName: String
    ): Response<UserDetailResp> {
        val fakeData = FakeResp.getUserDetailResp()
        return Response.success(fakeData)
    }

    override suspend fun getUserList(
        query: String,
        page: Int,
        perPage: Int
    ): Response<UserListResp> {
        val fakeData = FakeResp.getUserListResp()
        return Response.success(fakeData)
    }

    override suspend fun getRepoList(
        userName: String,
        page: Int,
        perPage: Int
    ): Response<RepoListResp> {
        val fakeData = FakeResp.getRepoListResp().toTypedArray()
        return Response.success(fakeData)
    }
}