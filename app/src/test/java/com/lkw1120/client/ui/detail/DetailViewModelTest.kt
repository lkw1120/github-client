package com.lkw1120.client.ui.detail

import com.lkw1120.client.datasource.FakeRemoteDataSource
import com.lkw1120.client.domain.ReposUseCase
import com.lkw1120.client.domain.ReposUseCaseImpl
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.UsersUseCaseImpl
import com.lkw1120.client.repository.ReposRepository
import com.lkw1120.client.repository.ReposRepositoryImpl
import com.lkw1120.client.repository.UsersRepository
import com.lkw1120.client.repository.UsersRepositoryImpl
import com.lkw1120.client.ui.base.MainCoroutinesRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private val mainCoroutinesRule = MainCoroutinesRule()

    private lateinit var viewModel: DetailViewModel
    private lateinit var usersUseCase: UsersUseCase
    private lateinit var reposUseCase: ReposUseCase
    private lateinit var usersRepository: UsersRepository
    private lateinit var reposRepository: ReposRepository
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Before
    fun setUp() {
        usersRepository = UsersRepositoryImpl(fakeRemoteDataSource)
        reposRepository = ReposRepositoryImpl(fakeRemoteDataSource)
        usersUseCase = UsersUseCaseImpl(usersRepository)
        reposUseCase = ReposUseCaseImpl(reposRepository)
        viewModel = DetailViewModel(usersUseCase,reposUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getUserDetail() = mainCoroutinesRule.testScope.runTest {

    }

    @Test
    fun getRepoList() = mainCoroutinesRule.testScope.runTest {

    }

    @Test
    fun exceptionHandler() {
        assertThrows(
            Exception::class.java
        ) {
            viewModel.exceptionHandler(Exception())
        }
    }
}