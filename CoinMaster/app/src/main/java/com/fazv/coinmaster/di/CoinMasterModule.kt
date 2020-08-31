package com.fazv.coinmaster.di

import com.fazv.data.api.CoinMasterApi
import com.fazv.data.api.retrofit.RetrofitConfig
import com.fazv.data.model.mapper.CurrenciesListMapper
import com.fazv.data.model.mapper.CurrencyMapper
import com.fazv.data.model.mapper.Mapper
import com.fazv.data.model.response.CurrenciesListResponse
import com.fazv.data.model.response.CurrencyResponse
import com.fazv.data.model.vo.CurrenciesListVO
import com.fazv.domain.repository.CoinMasterRepository
import com.fazv.domain.usecase.CalculateTargetValueUseCase
import com.fazv.domain.usecase.GetCurrenciesListUseCase
import com.fazv.domain.usecase.GetCurrencyUseCase
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