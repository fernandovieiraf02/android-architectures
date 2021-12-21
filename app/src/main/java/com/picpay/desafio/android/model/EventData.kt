package com.picpay.desafio.android.model


sealed class EventData<T>(val data: T?)

class SuccessEvent<T>(data: T) : EventData<T>(data)

class ErrorEvent<T>(val error: Throwable?) : EventData<T>(null)

class LoadingEvent<T> : EventData<T>(null)
