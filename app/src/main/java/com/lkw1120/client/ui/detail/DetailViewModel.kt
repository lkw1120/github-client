package com.lkw1120.client.ui.detail

import com.lkw1120.client.domain.ReposUseCase
import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val reposUseCase: ReposUseCase
): BaseViewModel() {

}