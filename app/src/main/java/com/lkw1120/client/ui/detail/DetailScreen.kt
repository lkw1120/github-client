package com.lkw1120.client.ui.detail

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.lkw1120.client.R
import com.lkw1120.client.ui.component.EmptyState
import com.lkw1120.client.ui.component.ErrorState
import com.lkw1120.client.ui.component.LoadingState
import com.lkw1120.client.ui.component.RepoItem

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    userName: String,
    goBack: () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    SideEffect {
        viewModel.getUserDetail(userName)
        viewModel.getUserRepos(userName)
    }
    Column(
        modifier = Modifier
    ) {
        UserDetailScreen(
            viewModel = viewModel,
            goBack = goBack
        )
        RepoListScreen(
            viewModel = viewModel,
            goRepoUrl = { url ->
                val intent = CustomTabsIntent.Builder()
                    .build()
                intent.launchUrl(context, Uri.parse(url))
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel,
    goBack: () -> Unit
) {
    val userDetail by viewModel.userDetail.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(152.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        TopAppBar(
            modifier = Modifier
                .background(Color.Transparent),
            windowInsets = WindowInsets(0.dp),
            title = { },
            actions = {
                Icon(
                    modifier = Modifier
                        .padding(6.dp, 12.dp)
                        .size(36.dp)
                        .clickable { goBack() },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null
                )
            },
        )
        Row(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape),
                    model = userDetail.avatarUrl,
                    placeholder = painterResource(id = R.drawable.ic_profile),
                    error = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (userDetail.login.isNotBlank()) {
                    Text(
                        text = userDetail.login,
                        style = TextStyle(
                            fontSize = 24.sp
                        )
                    )
                    if (userDetail.name.isNotBlank()) {
                        Text(
                            modifier = Modifier,
                            text = userDetail.name,
                            style = TextStyle(
                                fontSize = 18.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_followers),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(
                                id = R.string.followers,
                                userDetail.followers
                            ),
                            style = TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_following),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(
                                id = R.string.following,
                                userDetail.following
                            ),
                            style = TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RepoListScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel,
    goRepoUrl: (String) -> Unit
) {
    val repoList = viewModel.repoList.collectAsLazyPagingItems()

    when(val loadState = repoList.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingState()
        }
        is LoadState.Error -> {
            ErrorState(
                message = loadState.error.message
            )
        }
        is LoadState.NotLoading -> {
            if (repoList.itemCount == 0) {
                EmptyState()
            }
            else {
                LazyColumn(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(repoList.itemCount) { index ->
                        if (0 < index) {
                            HorizontalDivider()
                        }
                        repoList[index]?.let { item ->
                            RepoItem(
                                item = item,
                                onClick = { goRepoUrl(item.htmlUrl) }
                            )
                        }
                    }
                }
            }
        }
    }
}