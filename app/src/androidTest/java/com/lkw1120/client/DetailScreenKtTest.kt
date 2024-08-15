package com.lkw1120.client

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lkw1120.client.domain.ReposUseCase
import com.lkw1120.client.domain.ReposUseCaseImpl
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.UsersUseCaseImpl
import com.lkw1120.client.repository.ReposRepository
import com.lkw1120.client.repository.ReposRepositoryImpl
import com.lkw1120.client.repository.UsersRepository
import com.lkw1120.client.repository.UsersRepositoryImpl
import com.lkw1120.client.ui.MainActivity
import com.lkw1120.client.ui.detail.DetailScreen
import com.lkw1120.client.ui.detail.DetailViewModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: DetailViewModel
    private lateinit var usersUseCase: UsersUseCase
    private lateinit var reposUseCase: ReposUseCase
    private lateinit var usersRepository: UsersRepository
    private lateinit var reposRepository: ReposRepository
    private val remoteDataSource = FakeRemoteDataSource()

    @Before
    fun setUp() {
        usersRepository = UsersRepositoryImpl(remoteDataSource)
        reposRepository = ReposRepositoryImpl(remoteDataSource)
        usersUseCase = UsersUseCaseImpl(usersRepository)
        reposUseCase = ReposUseCaseImpl(reposRepository)
        viewModel = DetailViewModel(usersUseCase, reposUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun detailScreenIsDisplayed() = runTest {
        composeRule.activity.setContent {
            DetailScreen(
                viewModel = viewModel,
                userName = "lkw1120",
                goBack = { }
            )
        }
        composeRule.onNodeWithTag("detailScreen").assertIsDisplayed()
        composeRule.onNodeWithTag("userDetailScreen").assertIsDisplayed()
        composeRule.onNodeWithTag("topAppBar").assertIsDisplayed()
        composeRule.onNodeWithTag("close").assertIsDisplayed()
        composeRule.onNodeWithTag("userDetail").assertIsDisplayed()
        composeRule.onNodeWithTag("userProfile").assertIsDisplayed()
        composeRule.onNodeWithTag("userInfo").assertIsDisplayed()
        composeRule.onNodeWithTag("userName").assertIsDisplayed()
        composeRule.onNodeWithTag("userFullName").assertIsDisplayed()
        composeRule.onNodeWithTag("userFollowers").assertIsDisplayed()
        composeRule.onNodeWithTag("userFollowing").assertIsDisplayed()
        composeRule.onNodeWithTag("repoListScreen").assertIsDisplayed()
    }
}