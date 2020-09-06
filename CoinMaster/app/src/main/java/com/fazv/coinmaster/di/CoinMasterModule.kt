package com.fazv.coinmaster.di

import com.fazv.coinmaster.viewmodel.CoinListViewModel
import com.fazv.coinmaster.viewmodel.MainViewModel
import com.fazv.domain.repository.CurrencyRepository
import com.fazv.domain.usecase.CalculateTargetValueUseCase
import com.fazv.domain.usecase.GetCurrenciesListUseCase
import com.fazv.domain.usecase.GetCurrencyUseCase
import dagger.Module
import dagger.Provides

@Module
class CoinMasterModule {

    @Provides
    fun providesMainViewModel(
        currencyUseCase: GetCurrencyUseCase,
        calculateTargetValueUseCase: CalculateTargetValueUseCase
    ) = MainViewModel(currencyUseCase, calculateTargetValueUseCase)

    @Provides
    fun providesCoinListViewModel(
        getCurrenciesListUseCase: GetCurrenciesListUseCase
    ) = CoinListViewModel(getCurrenciesListUseCase)
}