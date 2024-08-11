package com.lkw1120.client.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lkw1120.client.domain.ReposUseCase
import com.lkw1120.client.domain.model.RepoItem
import javax.inject.Inject

class RepoPagingSource @Inject constructor(
    private val reposUseCase: ReposUseCase,
    private val userName: String,
) : PagingSource<Int, RepoItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoItem> {
        return try {
            val page = params.key ?: 1
            val response = reposUseCase.getRepoList(userName,page)
            LoadResult.Page(
                data = response,
                prevKey = if(page == 1) null else page.minus(1),
                nextKey = if(response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}