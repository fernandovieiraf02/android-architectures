package com.fazv.coinmaster.di

import com.fazv.coinmaster.view.CoinListActivity
import com.fazv.coinmaster.view.MainActivity
import com.fazv.data.di.DataModule
import com.fazv.domain.di.DomainModule
import dagger.Component

@Component(modules = [CoinMasterModule::class, DataModule::class, DomainModule::class])
interface CoinMasterComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(coinListActivity: CoinListActivity)
}