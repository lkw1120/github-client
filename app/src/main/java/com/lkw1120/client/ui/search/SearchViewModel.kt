package com.lkw1120.client.ui.search

import com.lkw1120.client.domain.UsersUseCase
import com.lkw1120.client.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase
): BaseViewModel() {

}