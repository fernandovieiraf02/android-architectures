package com.fazv.coinmaster.di

import com.fazv.coinmaster.view.CoinListActivity
import com.fazv.coinmaster.view.MainActivity
import dagger.Component

@Component(modules = [CoinMasterModule::class])
interface CoinMasterComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(coinListActivity: CoinListActivity)
}