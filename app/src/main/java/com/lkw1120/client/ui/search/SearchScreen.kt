package com.lkw1120.client.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.lkw1120.client.R
import com.lkw1120.client.ui.component.EmptyState
import com.lkw1120.client.ui.component.ErrorState
import com.lkw1120.client.ui.component.LoadingState
import com.lkw1120.client.ui.component.UserItem

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    goUserDetail: (userName: String) -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .testTag("searchScreen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarScreen(
            viewModel = viewModel
        )
        UserListScreen(
            viewModel = viewModel,
            goUserDetail = goUserDetail
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel
) {
    val focusManager = LocalFocusManager.current
    var query by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .testTag("searchBarScreen"),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            modifier = Modifier
                .testTag("searchBar"),
            windowInsets = WindowInsets(0.dp),
            query = query,
            onQueryChange = {
                query = it
            },
            onSearch = {
                viewModel.getUserList(query)
                focusManager.clearFocus()
            },
            active = false,
            onActiveChange = {},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search)
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                )
            },
            content = {}
        )
    }
}

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    goUserDetail: (userName: String) -> Unit
) {
    val userList = viewModel.userList.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .testTag("userListScreen")
    ) {
        when (val loadState = userList.loadState.refresh) {
            is LoadState.Loading -> {
                LoadingState()
            }

            is LoadState.Error -> {
                ErrorState(
                    message = loadState.error.message
                )
            }

            is LoadState.NotLoading -> {
                if (userList.itemCount == 0) {
                    EmptyState()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        items(userList.itemCount) { index ->
                            userList[index]?.let { item ->
                                UserItem(
                                    item = item,
                                    onClick = {
                                        goUserDetail(item.login)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}