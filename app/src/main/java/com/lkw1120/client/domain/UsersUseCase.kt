package com.lkw1120.client.domain

import com.lkw1120.client.domain.mapper.toDomain
import com.lkw1120.client.domain.model.UserList
import com.lkw1120.client.repository.UsersRepository
import javax.inject.Inject

interface UsersUseCase {

    suspend fun getUserList(query: String, page: Int): UserList

}

class UsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
): UsersUseCase {

    override suspend fun getUserList(query: String, page: Int): UserList {
        return usersRepository.getUserList(query,page).toDomain()
    }

}