package com.fazv.coinmaster.di

import com.fazv.coinmaster.api.CoinMasterApi
import com.fazv.coinmaster.api.retrofit.RetrofitConfig
import com.fazv.coinmaster.model.mapper.CurrenciesListMapper
import com.fazv.coinmaster.model.mapper.CurrencyMapper
import com.fazv.coinmaster.model.mapper.Mapper
import com.fazv.coinmaster.model.response.CurrenciesListResponse
import com.fazv.coinmaster.model.response.CurrencyResponse
import com.fazv.coinmaster.model.vo.CurrenciesListVO
import com.fazv.coinmaster.repository.CoinMasterRepository
import com.fazv.coinmaster.usecase.CalculateTargetValueUseCase
import com.fazv.coinmaster.usecase.GetCurrenciesListUseCase
import com.fazv.coinmaster.usecase.GetCurrencyUseCase
import com.fazv.coinmaster.viewmodel.CoinListViewModel
import com.fazv.coinmaster.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class CoinMasterModule {
    @Provides
    fun providesServiceApi(): CoinMasterApi = RetrofitConfig()
        .getServiceApi(CoinMasterApi::class.java)

    @Provides
    fun providesMainViewModel(
        currencyUseCase: GetCurrencyUseCase,
        calculateTargetValueUseCase: CalculateTargetValueUseCase
    ) = MainViewModel(currencyUseCase, calculateTargetValueUseCase)

    @Provides
    fun providesCoinListViewModel(
        getCurrenciesListUseCase: GetCurrenciesListUseCase
    ) = CoinListViewModel(getCurrenciesListUseCase)

    @Provides
    fun providesCalculateUseCase() = CalculateTargetValueUseCase()

    @Provides
    fun providesCurrencyUseCase(
        repository: CoinMasterRepository,
        mapper: Mapper<CurrencyResponse, CurrenciesListVO>
    ) = GetCurrencyUseCase(repository, mapper)

    @Provides
    fun providesCurrenciesListUseCase(
        repository: CoinMasterRepository,
        mapper: Mapper<CurrenciesListResponse, CurrenciesListVO>
    ) = GetCurrenciesListUseCase(repository, mapper)

    @Provides
    fun providesCurrencyMapper(): Mapper<CurrencyResponse, CurrenciesListVO> =
        CurrencyMapper()

    @Provides
    fun providesCurrenciesListMapper(): Mapper<CurrenciesListResponse, CurrenciesListVO> =
        CurrenciesListMapper()

    @Provides
    fun providesRepository(
        serviceApi: CoinMasterApi
    ) = CoinMasterRepository(serviceApi)
}