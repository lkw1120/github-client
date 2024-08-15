package com.lkw1120.client

import com.lkw1120.client.datasource.RemoteDataSource
import com.lkw1120.client.datasource.remote.response.RepoListResp
import com.lkw1120.client.datasource.remote.response.UserDetailResp
import com.lkw1120.client.datasource.remote.response.UserListResp
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.buffer
import okio.source

class FakeRemoteDataSource: RemoteDataSource {

    override suspend fun getUserList(query: String, page: Int): UserListResp {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("response/UserList.json")
        val source = inputStream.source().buffer()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return moshi.adapter(UserListResp::class.java).fromJson(source)!!
    }

    override suspend fun getUserDetail(userName: String): UserDetailResp {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("response/UserDetail.json")
        val source = inputStream.source().buffer()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return moshi.adapter(UserDetailResp::class.java).fromJson(source)!!
    }

    override suspend fun getRepoList(userName: String, page: Int): RepoListResp {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("response/RepoList.json")
        val source = inputStream.source().buffer()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return moshi.adapter(RepoListResp::class.java).fromJson(source)!!
    }
}