package com.fazv.data.di

import android.app.Application
import android.content.Context
import com.fazv.data.api.CoinMasterApi
import com.fazv.data.api.retrofit.RetrofitConfig
import com.fazv.data.datasource.cachedata.CacheDataSource
import com.fazv.data.datasource.cachedata.CacheDataSourceImpl
import com.fazv.data.datasource.cachedata.CurrenciesListDAO
import com.fazv.data.datasource.cachedata.CurrenciesListDataBase
import com.fazv.data.datasource.remotedata.RemoteDataSource
import com.fazv.data.datasource.remotedata.RemoteDataSourceImpl
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
import javax.inject.Singleton

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
        cacheDataSource: CacheDataSource,
        remoteDataSource: RemoteDataSource
    ): CurrencyRepository =
        CoinMasterRepositoryImpl(cacheDataSource, remoteDataSource)

    @Provides
    @Singleton
    fun providesCacheDataSource(
        currenciesListDAO: CurrenciesListDAO
    ): CacheDataSource = CacheDataSourceImpl(currenciesListDAO)

    @Provides
    fun providesRemoteDataSource(
        serviceApi: CoinMasterApi
    ): RemoteDataSource = RemoteDataSourceImpl(serviceApi)

    @Provides
    @Singleton
    fun providesCurrenciesListDAO(
        context: Application
    ): CurrenciesListDAO =
        CurrenciesListDataBase.createDataBase(context)
}