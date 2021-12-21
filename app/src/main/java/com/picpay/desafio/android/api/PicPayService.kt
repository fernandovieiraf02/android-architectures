package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import io.reactivex.Single
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    fun getUsers(): Single<List<User>>
}