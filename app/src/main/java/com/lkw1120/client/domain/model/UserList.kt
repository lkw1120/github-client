package com.lkw1120.client.domain.model

data class UserList (
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<UserItem>,
)