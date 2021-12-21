package com.picpay.desafio.android.extension

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.model.ErrorEvent
import com.picpay.desafio.android.model.EventData
import com.picpay.desafio.android.model.LoadingEvent
import com.picpay.desafio.android.model.SuccessEvent

fun <T> MutableLiveData<EventData<T>>.postLoading() {
    this.postValue(LoadingEvent())
}

fun <T> MutableLiveData<EventData<T>>.postSuccess(data: T) {
    this.postValue(SuccessEvent(data))
}

fun <T> MutableLiveData<EventData<T>>.postError(data: Throwable?) {
    this.postValue(ErrorEvent(data))
}