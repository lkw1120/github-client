package com.lkw1120.client.domain.model

data class UserDetail (
    val login: String,
    val avatarUrl: String,
    val name: String,
    val followers: Long,
    val following: Long,
)