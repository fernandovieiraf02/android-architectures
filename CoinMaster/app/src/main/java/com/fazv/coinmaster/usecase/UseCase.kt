package com.fazv.coinmaster.usecase

interface UseCase<T, U> {
    fun execute(param: T): U
}
