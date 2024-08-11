package com.lkw1120.client.datasource.remote

import com.lkw1120.client.common.Constants.PAGE_SIZE
import com.lkw1120.client.datasource.remote.response.RepoListResp
import com.lkw1120.client.datasource.remote.response.UserDetailResp
import com.lkw1120.client.datasource.remote.response.UserListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getUserList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PAGE_SIZE
    ): Response<UserListResp>


    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") userName: String
    ): Response<UserDetailResp>


    @GET("users/{username}/repos")
    suspend fun getRepoList(
        @Path("username") userName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PAGE_SIZE
    ): Response<RepoListResp>
}