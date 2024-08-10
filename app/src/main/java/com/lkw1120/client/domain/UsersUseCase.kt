package com.lkw1120.client.domain

import com.lkw1120.client.repository.UsersRepository
import javax.inject.Inject

interface UsersUseCase {

}

class UsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
): UsersUseCase {

}