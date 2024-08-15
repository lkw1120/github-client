package com.lkw1120.client.repository

import com.lkw1120.client.datasource.FakeRemoteDataSource
import com.lkw1120.client.domain.model.FakeModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UsersRepositoryImplTest {

    private lateinit var usersRepository: UsersRepository
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Before
    fun setUp() {
        usersRepository = UsersRepositoryImpl(fakeRemoteDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getUserList() = runTest {
        val response = usersRepository.getUserList("lkw1120",1)
        val fakeData = FakeModel.getUserList()
        Assert.assertEquals(response.totalCount, fakeData.totalCount)
        Assert.assertEquals(response.incompleteResults, fakeData.incompleteResults)
        Assert.assertEquals(response.items?.get(0)?.login, fakeData.items[0].login)
        Assert.assertEquals(response.items?.get(0)?.htmlUrl, fakeData.items[0].htmlUrl)
        Assert.assertEquals(response.items?.get(0)?.avatarUrl, fakeData.items[0].avatarUrl)
        Assert.assertEquals(response.items?.get(0)?.id, fakeData.items[0].id)
        Assert.assertEquals(response.items?.get(0)?.type, fakeData.items[0].type)
        Assert.assertEquals(response.items?.get(0)?.score, fakeData.items[0].score)
        Assert.assertEquals(response.items?.get(0)?.url, fakeData.items[0].url)
        Assert.assertEquals(response.items?.get(0)?.gravatarId, fakeData.items[0].gravatarId)
        Assert.assertEquals(response.items?.get(0)?.nodeId, fakeData.items[0].nodeId)
        Assert.assertEquals(response.items?.get(0)?.organizationsUrl, fakeData.items[0].organizationsUrl)
        Assert.assertEquals(response.items?.get(0)?.reposUrl, fakeData.items[0].reposUrl)
        Assert.assertEquals(response.items?.get(0)?.eventsUrl, fakeData.items[0].eventsUrl)
        Assert.assertEquals(response.items?.get(0)?.receivedEventsUrl, fakeData.items[0].receivedEventsUrl)
        Assert.assertEquals(response.items?.get(0)?.followersUrl, fakeData.items[0].followersUrl)
        Assert.assertEquals(response.items?.get(0)?.followingUrl, fakeData.items[0].followingUrl)
        Assert.assertEquals(response.items?.get(0)?.gistsUrl, fakeData.items[0].gistsUrl)
        Assert.assertEquals(response.items?.get(0)?.starredUrl, fakeData.items[0].starredUrl)
        Assert.assertEquals(response.items?.get(0)?.subscriptionsUrl, fakeData.items[0].subscriptionsUrl)
        Assert.assertEquals(response.items?.get(0)?.siteAdmin, fakeData.items[0].siteAdmin)
    }

    @Test
    fun getUserDetail() = runTest {
        val response = usersRepository.getUserDetail("lkw1120")
        val fakeData = FakeModel.getUserDetail()
        Assert.assertEquals(response.login, fakeData.login)
        Assert.assertEquals(response.name, fakeData.name)
        Assert.assertEquals(response.avatarUrl, fakeData.avatarUrl)
        Assert.assertEquals(response.followers, fakeData.followers)
        Assert.assertEquals(response.following, fakeData.following)
    }
}