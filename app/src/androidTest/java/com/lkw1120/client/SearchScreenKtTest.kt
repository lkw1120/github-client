package com.lkw1120.client

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.UsersUseCaseImpl
import com.lkw1120.client.repository.UsersRepository
import com.lkw1120.client.repository.UsersRepositoryImpl
import com.lkw1120.client.ui.MainActivity
import com.lkw1120.client.ui.search.SearchScreen
import com.lkw1120.client.ui.search.SearchViewModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: SearchViewModel
    private lateinit var usersUseCase: UsersUseCase
    private lateinit var usersRepository: UsersRepository
    private val remoteDataSource = FakeRemoteDataSource()

    @Before
    fun setUp() {
        usersRepository = UsersRepositoryImpl(remoteDataSource)
        usersUseCase = UsersUseCaseImpl(usersRepository)
        viewModel = SearchViewModel(usersUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun searchScreenIsDisplayed() = runTest {
        composeRule.activity.setContent {
            SearchScreen(
                viewModel = viewModel,
                goUserDetail = { }
            )
        }
        composeRule.onNodeWithTag("searchScreen").assertIsDisplayed()
        composeRule.onNodeWithTag("searchBarScreen").assertIsDisplayed()
        composeRule.onNodeWithTag("searchBar").assertIsDisplayed()
        composeRule.onNodeWithTag("userListScreen").assertIsDisplayed()
    }
}