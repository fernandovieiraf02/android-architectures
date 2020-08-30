package com.fazv.coinmaster

import android.app.Application
import com.fazv.coinmaster.di.CoinMasterComponent
import com.fazv.coinmaster.di.DaggerCoinMasterComponent

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerCoinMasterComponent
            .builder()
            .build()
    }

    companion object {
        private var component: CoinMasterComponent? = null
        fun getComponent(): CoinMasterComponent? {
            return component
        }
    }
}