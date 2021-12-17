package com.picpay.desafio.android

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.data.User

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}