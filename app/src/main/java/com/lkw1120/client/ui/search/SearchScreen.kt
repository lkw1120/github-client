package com.lkw1120.client.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.lkw1120.client.R
import com.lkw1120.client.ui.component.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigate: (userName: String) -> Unit
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val focusManager = LocalFocusManager.current

    val width = configuration.screenWidthDp.dp
    val height = configuration.screenHeightDp.dp

    var query by remember { mutableStateOf("") }

    val userList = viewModel.userItemList.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                SearchBar(
                    modifier = Modifier,
                    query = query,
                    onQueryChange = {
                        query = it
                    },
                    onSearch = {
                        viewModel.getUserList(query)
                        focusManager.clearFocus()
                    },
                    active = false,
                    onActiveChange = {

                    },
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
            if(userList.itemCount == 0) {
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
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(userList.itemCount) { index ->
                        userList[index]?.let { item ->
                            UserItem(
                                item = item,
                                onClick = { onNavigate(item.login) }
                            )
                        }
                    }
                }
            }
        }
    }
}