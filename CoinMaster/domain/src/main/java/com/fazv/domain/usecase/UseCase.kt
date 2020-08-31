package com.fazv.domain.usecase

interface UseCase<T, U> {
    fun execute(param: T): U
}
