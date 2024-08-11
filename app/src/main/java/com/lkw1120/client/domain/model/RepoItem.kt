package com.lkw1120.client.domain.model

data class RepoItem (
    val name: String,
    val fullName: String,
    val htmlUrl: String,
    val language: String,
    val stargazersCount: Long,
    val description: String,
    val fork: Boolean,
)