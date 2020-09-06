package com.fazv.data.di

import com.fazv.data.api.CoinMasterApi
import com.fazv.data.api.retrofit.RetrofitConfig
import com.fazv.data.mapper.CurrenciesListMapper
import com.fazv.data.mapper.CurrencyMapper
import com.fazv.data.mapper.Mapper
import com.fazv.data.model.response.CurrenciesListResponse
import com.fazv.data.model.response.CurrencyResponse
import com.fazv.data.repositoryimpl.CoinMasterRepositoryImpl
import com.fazv.domain.entities.CurrenciesListDTO
import com.fazv.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun providesServiceApi(): CoinMasterApi = RetrofitConfig()
        .getServiceApi(CoinMasterApi::class.java)

    @Provides
    fun providesCurrencyMapper(): Mapper<CurrencyResponse, CurrenciesListDTO> =
        CurrencyMapper()

    @Provides
    fun providesCurrenciesListMapper(): Mapper<CurrenciesListResponse, CurrenciesListDTO> =
        CurrenciesListMapper()

    @Provides
    fun providesRepository(
        serviceApi: CoinMasterApi
    ): CurrencyRepository = CoinMasterRepositoryImpl(serviceApi)
}