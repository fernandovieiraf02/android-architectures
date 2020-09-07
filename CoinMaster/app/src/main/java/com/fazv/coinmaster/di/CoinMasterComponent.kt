package com.fazv.coinmaster.di

import android.app.Application
import com.fazv.coinmaster.view.CoinListActivity
import com.fazv.coinmaster.view.MainActivity
import com.fazv.data.di.DataModule
import com.fazv.domain.di.DomainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CoinMasterModule::class, DataModule::class, DomainModule::class])
interface CoinMasterComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(coinListActivity: CoinListActivity)

    @Component.Builder
    interface Builder {
        fun build(): CoinMasterComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}