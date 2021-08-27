package com.fazv.coinmaster

import android.app.Application
import android.content.Context
import com.fazv.coinmaster.di.CoinMasterComponent
import com.fazv.coinmaster.di.DaggerCoinMasterComponent

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        contextApplication = this
        initDagger()
    }

    private fun initDagger() {

        component = DaggerCoinMasterComponent
            .builder()
            .application(this)
            .build()
    }

    companion object {
        private var component: CoinMasterComponent? = null
        lateinit var contextApplication: Context

        fun getComponent(): CoinMasterComponent? {
            return component
        }
    }
}