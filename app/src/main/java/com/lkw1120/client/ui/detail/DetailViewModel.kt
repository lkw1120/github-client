package com.lkw1120.client.ui.detail

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.lkw1120.client.common.Constants.PAGE_SIZE
import com.lkw1120.client.domain.ReposUseCase
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.domain.model.RepoItem
import com.lkw1120.client.domain.model.UserDetail
import com.lkw1120.client.ui.base.BaseViewModel
import com.lkw1120.client.ui.paging.RepoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val reposUseCase: ReposUseCase
): BaseViewModel() {

    private val _userDetail: MutableStateFlow<UserDetail> =
        MutableStateFlow(UserDetail("","","",0,0))
    val userDetail: StateFlow<UserDetail>
        get() = _userDetail

    private val _repoList: MutableStateFlow<PagingData<RepoItem>> =
        MutableStateFlow(PagingData.empty())
    val repoList: Flow<PagingData<RepoItem>>
        get() = _repoList


    fun getUserDetail(userName: String) = workerScope.launch {
        _userDetail.value = usersUseCase.getUserDetail(userName)
    }

    fun getRepoList(userName: String) = workerScope.launch {
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                RepoPagingSource(
                    reposUseCase = reposUseCase,
                    userName = userName
                )
            }
        ).flow.cachedIn(workerScope).collect {
            _repoList.value = it.filter { item -> !item.fork }
        }
    }

    override fun exceptionHandler(throwable: Throwable) {
        super.exceptionHandler(throwable)
        error(throwable.stackTraceToString())
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}