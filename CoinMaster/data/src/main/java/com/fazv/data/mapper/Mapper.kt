package com.fazv.data.mapper

interface Mapper<S,T> {
    fun map(from: S): T
}