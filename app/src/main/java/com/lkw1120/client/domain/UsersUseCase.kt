package com.lkw1120.client.domain

import com.lkw1120.client.domain.mapper.toDomain
import com.lkw1120.client.domain.model.UserDetail
import com.lkw1120.client.domain.model.UserList
import com.lkw1120.client.repository.UsersRepository
import javax.inject.Inject

interface UsersUseCase {

    suspend fun getUserList(query: String, page: Int): UserList

    suspend fun getUserDetail(userName: String): UserDetail
}

class UsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
): UsersUseCase {

    override suspend fun getUserList(query: String, page: Int): UserList {
        return usersRepository.getUserList(query,page).toDomain()
    }

    override suspend fun getUserDetail(userName: String): UserDetail {
        return usersRepository.getUserDetail(userName).toDomain()
    }
}