package com.lkw1120.client.datasource.remote

import com.lkw1120.client.datasource.remote.response.FakeResp
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiServiceTest: AbstractApi<ApiService>() {

    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = createService(ApiService::class.java)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getUserListSuccess() = runTest {
        enqueueSuccessResponse("UserList.json")
        val response = apiService.getUserList("lkw1120",1)
        val fakeData = FakeResp.getUserListResp()

        Assert.assertEquals(response.code(), 200)
        val responseBody = requireNotNull(response.body())

        Assert.assertEquals(responseBody.totalCount, fakeData.totalCount)
        Assert.assertEquals(responseBody.incompleteResults, fakeData.incompleteResults)
        Assert.assertEquals(responseBody.items?.get(0)?.login, fakeData.items?.get(0)?.login)
        Assert.assertEquals(responseBody.items?.get(0)?.avatarUrl, fakeData.items?.get(0)?.avatarUrl)
    }

    @Test
    fun getUserListError() = runTest {
        enqueueSuccessResponse("UserList.json")
        val response = apiService.getUserList("lkw1120",1)

        Assert.assertEquals(response.code(), 200)
        Assert.assertThrows(Exception::class.java) {
            throw Exception(response.errorBody()?.string())
        }
    }

    @Test
    fun getUserListFailure() = runTest {
        enqueueFailureResponse("UserList.json")
        val response = apiService.getUserList("lkw1120",1)
        Assert.assertEquals(response.code(), 404)
        Assert.assertThrows(Exception::class.java) {
            throw Exception(response.errorBody()?.string())
        }
    }

    @Test
    fun getUserDetailSuccess() = runTest {
        enqueueSuccessResponse("UserDetail.json")
        val response = apiService.getUserDetail("lkw1120")
        val fakeData = FakeResp.getUserDetailResp()

        Assert.assertEquals(response.code(), 200)
        val responseBody = requireNotNull(response.body())

        Assert.assertEquals(responseBody.login, fakeData.login)
        Assert.assertEquals(responseBody.id, fakeData.id)
        Assert.assertEquals(responseBody.avatarUrl, fakeData.avatarUrl)
        Assert.assertEquals(responseBody.name, fakeData.name)
        Assert.assertEquals(responseBody.followers, fakeData.followers)
        Assert.assertEquals(responseBody.following, fakeData.following)
    }

    @Test
    fun getUserDetailError() = runTest {
        enqueueSuccessResponse("UserDetail.json")
        val response = apiService.getUserDetail("lkw1120")

        Assert.assertEquals(response.code(), 200)
        Assert.assertThrows(Exception::class.java) {
            throw Exception(response.errorBody()?.string())
        }
    }

    @Test
    fun getUserDetailFailure() = runTest {
        enqueueFailureResponse("UserDetail.json")
        val response = apiService.getUserDetail("lkw1120")

        Assert.assertEquals(response.code(), 404)
        Assert.assertThrows(Exception::class.java) {
            throw Exception(response.errorBody()?.string())
        }
    }

    @Test
    fun getRepoListSuccess() = runTest {
        enqueueSuccessResponse("RepoList.json")
        val response = apiService.getRepoList("lkw1120",1)
        val fakeData = FakeResp.getRepoListResp()

        Assert.assertEquals(response.code(), 200)
        val responseBody = requireNotNull(response.body())

        Assert.assertEquals(responseBody[0].name, fakeData[0].name)
        Assert.assertEquals(responseBody[0].htmlUrl, fakeData[0].htmlUrl)
    }

    @Test
    fun getRepoListError() = runTest {
        enqueueSuccessResponse("RepoList.json")
        val response = apiService.getRepoList("lkw1120",1)
        Assert.assertEquals(response.code(), 200)
        Assert.assertThrows(Exception::class.java) {
            throw Exception(response.errorBody()?.string())
        }
    }

    @Test
    fun getRepoListFailure() = runTest {
        enqueueFailureResponse("RepoList.json")
        val response = apiService.getRepoList("lkw1120",1)
        Assert.assertEquals(response.code(), 404)
        Assert.assertThrows(Exception::class.java) {
            throw Exception(response.errorBody()?.string())
        }
    }
}