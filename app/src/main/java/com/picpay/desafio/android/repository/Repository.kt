package com.picpay.desafio.android.repository

import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.extension.makeObservable

class Repository(private val service: PicPayService) {

    fun getUsers() = service
        .getUsers()
        .makeObservable()
}