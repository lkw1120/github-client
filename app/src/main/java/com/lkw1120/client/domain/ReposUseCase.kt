package com.lkw1120.client.domain

import com.lkw1120.client.domain.mapper.toDomain
import com.lkw1120.client.domain.model.RepoList
import com.lkw1120.client.repository.ReposRepository
import javax.inject.Inject

interface ReposUseCase {

    suspend fun getRepoList(userName: String, page: Int): RepoList

}

class ReposUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository
): ReposUseCase {

    override suspend fun getRepoList(userName: String, page: Int): RepoList {
        return reposRepository.getRepoList(userName,page).toDomain()
    }
}