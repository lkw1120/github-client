package com.lkw1120.client.ui.detail

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.lkw1120.client.R
import com.lkw1120.client.ui.component.RepoItem

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    userName: String
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val width = configuration.screenWidthDp.dp
    val height = configuration.screenHeightDp.dp

    val userDetail by viewModel.userDetail.collectAsState()
    val repoList = viewModel.repoList.collectAsLazyPagingItems()

    viewModel.getUserDetail(userName)
    viewModel.getUserRepos(userName)

    Scaffold(
        modifier = Modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(152.dp)
            ){
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
                        contentDescription = null,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if(userDetail.login.isNotBlank()) {
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
            if(repoList.itemCount == 0) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .width(width * 0.5f)
                            .wrapContentHeight(),
                        painter = painterResource(id = R.drawable.ic_inspectocat),
                        contentDescription = null,
                    )
                }
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
                                onClick = {
                                    val intent = CustomTabsIntent.Builder()
                                        .build()
                                    intent.launchUrl(context, Uri.parse(item.htmlUrl))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}