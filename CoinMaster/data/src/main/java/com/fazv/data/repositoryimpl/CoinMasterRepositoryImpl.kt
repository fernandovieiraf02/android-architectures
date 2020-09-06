package com.fazv.data.repositoryimpl

import com.fazv.data.datasource.cachedata.CacheDataSource
import com.fazv.data.datasource.remotedata.RemoteDataSource
import com.fazv.domain.entities.CurrenciesListDTO
import com.fazv.domain.repository.CurrencyRepository
import com.fazv.utils.extension.makeRequest
import io.reactivex.Single

class CoinMasterRepositoryImpl(
    private val cacheDataSource: CacheDataSource,
    private val remoteDataSource: RemoteDataSource
) : CurrencyRepository {

    override fun getCurrenciesList(shouldUpdate: Boolean): Single<CurrenciesListDTO> {
        return if (shouldUpdate) {
            getCurrenciesFromRemote(shouldUpdate)
        } else {
            cacheDataSource
                .getCurrenciesList()
                .flatMap { currencies ->
                    handleCurrenciesFromCache(currencies)
                }
        }
    }

    private fun getCurrenciesFromRemote(shouldUpdate: Boolean): Single<CurrenciesListDTO> {
        return remoteDataSource
            .getCurrenciesList()
            .flatMap { currencies ->
                if (shouldUpdate) {
                    cacheDataSource.updateData(currencies)
                } else {
                    cacheDataSource.insert(currencies)
                }
                Single.just(currencies)
            }
    }

    private fun handleCurrenciesFromCache(currencies: List<CurrenciesListDTO>): Single<CurrenciesListDTO> {
        return if (currencies.isEmpty()) {
            remoteDataSource.getCurrenciesList()
        } else {
            Single.just(currencies.first())
        }
    }

    override fun getCurrency(coin: String): Single<CurrenciesListDTO> =
        makeRequest {
            remoteDataSource.getCurrency(coin)
        }
}