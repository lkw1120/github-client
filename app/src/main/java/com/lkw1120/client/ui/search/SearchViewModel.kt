package com.lkw1120.client.ui.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lkw1120.client.common.Constants.PAGE_SIZE
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.model.UserItem
import com.lkw1120.client.ui.base.BaseViewModel
import com.lkw1120.client.ui.paging.UserPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase
): BaseViewModel() {

    private var _userList: MutableStateFlow<PagingData<UserItem>> =
        MutableStateFlow(PagingData.empty())
    val userList: Flow<PagingData<UserItem>>
        get() = _userList

    fun getUserList(query: String) = workerScope.launch {
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                UserPagingSource(
                    usersUseCase = usersUseCase,
                    query = query
                )
            }
        ).flow.cachedIn(workerScope).collect {
            _userList.value = it
        }
    }

    override fun exceptionHandler(throwable: Throwable) {
        super.exceptionHandler(throwable)
        error(throwable.stackTraceToString())
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }
}