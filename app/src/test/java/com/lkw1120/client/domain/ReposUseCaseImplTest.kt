package com.lkw1120.client.domain

import com.lkw1120.client.datasource.FakeRemoteDataSource
import com.lkw1120.client.datasource.remote.AbstractApi
import com.lkw1120.client.datasource.remote.ApiService
import com.lkw1120.client.datasource.remote.response.FakeResp
import com.lkw1120.client.domain.mapper.toDomain
import com.lkw1120.client.repository.ReposRepository
import com.lkw1120.client.repository.ReposRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ReposUseCaseImplTest: AbstractApi<ApiService>() {

    private lateinit var reposUseCase: ReposUseCase
    private lateinit var reposRepository: ReposRepository
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Before
    fun setUp() {
        reposRepository = ReposRepositoryImpl(fakeRemoteDataSource)
        reposUseCase = ReposUseCaseImpl(reposRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getRepoList() = runTest {
        val response = reposUseCase.getRepoList("lkw1120",1)
        val fakeData = FakeResp.getRepoListResp().map { it.toDomain() }
        Assert.assertEquals(response[0].name, fakeData[0].name)
        Assert.assertEquals(response[0].htmlUrl, fakeData[0].htmlUrl)
        Assert.assertEquals(response[0].description, fakeData[0].description)
        Assert.assertEquals(response[0].stargazersCount, fakeData[0].stargazersCount)
        Assert.assertEquals(response[0].language, fakeData[0].language)
        Assert.assertEquals(response[0].fork, fakeData[0].fork)
    }
}