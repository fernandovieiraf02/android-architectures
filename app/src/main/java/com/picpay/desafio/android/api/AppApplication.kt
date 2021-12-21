package com.picpay.desafio.android.api

import android.app.Application
import com.picpay.desafio.android.di.KoinModule
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(getKoinModulesList())
        }
    }

    private fun getKoinModulesList() = listOf(
        KoinModule.repositories,
        KoinModule.viewModels,
        KoinModule.useCases,
        KoinModule.services
    )
}