package com.lkw1120.client.datasource

import com.lkw1120.client.datasource.remote.ApiService
import com.lkw1120.client.datasource.remote.FakeApiService
import com.lkw1120.client.datasource.remote.response.FakeResp
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = FakeApiService()
        remoteDataSource = RemoteDataSourceImpl(apiService)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getUserList() = runTest {
        val response = remoteDataSource.getUserList("lkw1120",1)
        val fakeData = FakeResp.getUserListResp()

        Assert.assertEquals(response.totalCount, fakeData.totalCount)
        Assert.assertEquals(response.incompleteResults, fakeData.incompleteResults)
        Assert.assertEquals(response.items?.get(0)?.login, fakeData.items?.get(0)?.login)
        Assert.assertEquals(response.items?.get(0)?.avatarUrl, fakeData.items?.get(0)?.avatarUrl)
    }

    @Test
    fun getUserDetail() = runTest {
        val response = remoteDataSource.getUserDetail("lkw1120")
        val fakeData = FakeResp.getUserDetailResp()

        Assert.assertEquals(response.login, fakeData.login)
        Assert.assertEquals(response.avatarUrl, fakeData.avatarUrl)
        Assert.assertEquals(response.name, fakeData.name)
        Assert.assertEquals(response.followers, fakeData.followers)
        Assert.assertEquals(response.following, fakeData.following)
    }

    @Test
    fun getRepoList() = runTest {
        val response = remoteDataSource.getRepoList("lkw1120",1)
        val fakeData = FakeResp.getRepoListResp()

        Assert.assertEquals(response[0].name, fakeData[0].name)
        Assert.assertEquals(response[0].htmlUrl, fakeData[0].htmlUrl)
    }
}