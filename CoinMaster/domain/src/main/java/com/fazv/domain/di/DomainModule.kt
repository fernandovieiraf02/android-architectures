package com.fazv.domain.di

import com.fazv.domain.repository.CurrencyRepository
import com.fazv.domain.usecase.CalculateTargetValueUseCase
import com.fazv.domain.usecase.GetCurrenciesListUseCase
import com.fazv.domain.usecase.GetCurrencyUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesCalculateUseCase() = CalculateTargetValueUseCase()

    @Provides
    fun providesCurrencyUseCase(
        repository: CurrencyRepository
    ) = GetCurrencyUseCase(repository)

    @Provides
    fun providesCurrenciesListUseCase(
        repository: CurrencyRepository
    ) = GetCurrenciesListUseCase(repository)
}