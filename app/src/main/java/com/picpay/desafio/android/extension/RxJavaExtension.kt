package com.picpay.desafio.android.extension

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.makeObservable() = this
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())