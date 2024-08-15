package com.lkw1120.client.ui.search

import com.lkw1120.client.datasource.FakeRemoteDataSource
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.UsersUseCaseImpl
import com.lkw1120.client.repository.UsersRepository
import com.lkw1120.client.repository.UsersRepositoryImpl
import com.lkw1120.client.ui.base.MainCoroutinesRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    private val mainCoroutinesRule = MainCoroutinesRule()

    private lateinit var viewModel: SearchViewModel
    private lateinit var usersUseCase: UsersUseCase
    private lateinit var usersRepository: UsersRepository
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Before
    fun setUp() {
        usersRepository = UsersRepositoryImpl(fakeRemoteDataSource)
        usersUseCase = UsersUseCaseImpl(usersRepository)
        viewModel = SearchViewModel(usersUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getUserList() = mainCoroutinesRule.testScope.runTest {

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