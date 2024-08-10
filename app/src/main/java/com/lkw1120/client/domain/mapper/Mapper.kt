package com.lkw1120.client.domain.mapper

import com.lkw1120.client.datasource.remote.response.UserItemResp
import com.lkw1120.client.datasource.remote.response.UserListResp
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