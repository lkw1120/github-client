package com.lkw1120.client.domain.mapper

import com.lkw1120.client.datasource.remote.response.RepoItemResp
import com.lkw1120.client.datasource.remote.response.RepoListResp
import com.lkw1120.client.datasource.remote.response.UserDetailResp
import com.lkw1120.client.datasource.remote.response.UserItemResp
import com.lkw1120.client.datasource.remote.response.UserListResp
import com.lkw1120.client.domain.model.RepoItem
import com.lkw1120.client.domain.model.RepoList
import com.lkw1120.client.domain.model.UserDetail
import com.lkw1120.client.domain.model.UserItem
import com.lkw1120.client.domain.model.UserList

fun UserListResp.toDomain(): UserList {
    return UserList(
        totalCount = this.totalCount?:0,
        incompleteResults = this.incompleteResults?:false,
        items = this.items?.map { it.toDomain() }?: emptyList()
    )
}

fun UserItemResp.toDomain(): UserItem {
    return UserItem(
        login = this.login?:"",
        id = this.id?:0,
        nodeId = this.nodeId?:"",
        avatarUrl = this.avatarUrl?:"",
        gravatarId = this.gravatarId?:"",
        url = this.url?:"",
        htmlUrl = this.htmlUrl?:"",
        followersUrl = this.followersUrl?:"",
        followingUrl = this.followingUrl?:"",
        gistsUrl = this.gistsUrl?:"",
        starredUrl = this.starredUrl?:"",
        subscriptionsUrl = this.subscriptionsUrl?:"",
        organizationsUrl = this.organizationsUrl?:"",
        reposUrl = this.reposUrl?:"",
        eventsUrl = this.eventsUrl?:"",
        receivedEventsUrl = this.receivedEventsUrl?:"",
        type = this.type?:"",
        siteAdmin = this.siteAdmin?:false,
        score = this.score?:0.0,
    )
}


fun UserDetailResp.toDomain(): UserDetail {
    return UserDetail(
        login = this.login?:"",
        avatarUrl = this.avatarUrl?:"",
        name = this.name?:"",
        followers = this.followers?:0,
        following = this.following?:0,
    )
}

fun RepoListResp.toDomain(): RepoList {
    return this.map { it.toDomain() }
}

fun RepoItemResp.toDomain(): RepoItem {
    return RepoItem(
        name = this.name?:"",
        fullName = this.fullName?:"",
        htmlUrl = this.htmlUrl?:"",
        language = this.language?:"Unknown",
        stargazersCount = this.stargazersCount?:0,
        description = this.description?:"",
        fork = this.fork?:false
    )
}