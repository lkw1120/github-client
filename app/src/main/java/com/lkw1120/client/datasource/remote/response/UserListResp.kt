package com.lkw1120.client.datasource.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListResp (
    @field:Json(name = "total_count")
    val totalCount: Long?,
    @field:Json(name = "incomplete_results")
    val incompleteResults: Boolean?,
    @field:Json(name = "items")
    val items: List<UserItemResp>?,
)