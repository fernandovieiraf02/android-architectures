package com.fazv.coinmaster.model.mapper

interface Mapper<S,T> {
    fun map(from: S): T
}