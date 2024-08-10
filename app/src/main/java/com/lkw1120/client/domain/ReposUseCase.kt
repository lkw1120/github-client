package com.lkw1120.client.domain

import com.lkw1120.client.repository.ReposRepository
import javax.inject.Inject

interface ReposUseCase {

}

class ReposUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository
): ReposUseCase {

}