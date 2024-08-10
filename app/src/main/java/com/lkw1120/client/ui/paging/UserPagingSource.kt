package com.lkw1120.client.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.model.UserItem
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val query: String,
) : PagingSource<Int, UserItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        return try {
            val page = params.key ?: 1
            val response = usersUseCase.getUserList(query,page)
            LoadResult.Page(
                data = response.items,
                prevKey = if(page == 1) null else page.minus(1),
                nextKey = if(response.items.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}