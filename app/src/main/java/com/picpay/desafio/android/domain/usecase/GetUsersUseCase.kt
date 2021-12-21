package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.repository.Repository

class GetUsersUseCase(private val repository: Repository) {
    fun execute() = repository.getUsers()
}