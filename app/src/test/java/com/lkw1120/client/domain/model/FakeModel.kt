package com.lkw1120.client.domain.model

object FakeModel {

    fun getRepoList() = listOf(
        RepoItem(
            name = "github-client",
            fullName = "lkw1120/github-client",
            htmlUrl = "https://github.com/lkw1120/github-client",
            description = "Simple Github Client app using Jetpack Compose",
            stargazersCount = 0,
            language = "Kotlin",
            fork = false,
        )
    )

    fun getUserDetail() = UserDetail(
        login = "lkw1120",
        avatarUrl = "https://avatars.githubusercontent.com/u/15232122?v=4",
        name = "lkw1120",
        followers = 1,
        following = 7,
    )

    fun getUserList() = UserList(
        totalCount = 1,
        incompleteResults = false,
        items = listOf(
            UserItem(
                login = "lkw1120",
                id = 15232122,
                nodeId = "MDQ6VXNlcjE1MjMyMTIy",
                avatarUrl ="https://avatars.githubusercontent.com/u/15232122?v=4",
                gravatarId = "",
                url ="https://api.github.com/users/lkw1120",
                htmlUrl = "https://github.com/lkw1120",
                followersUrl = "https://api.github.com/users/lkw1120/followers",
                followingUrl = "https://api.github.com/users/lkw1120/following{/other_user}",
                gistsUrl = "https://api.github.com/users/lkw1120/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/lkw1120/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/lkw1120/subscriptions",
                organizationsUrl = "https://api.github.com/users/lkw1120/orgs",
                reposUrl = "https://api.github.com/users/lkw1120/repos",
                eventsUrl = "https://api.github.com/users/lkw1120/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/lkw1120/received_events",
                type = "User",
                siteAdmin = false,
                score = 1.0
            )
        )
    )
}