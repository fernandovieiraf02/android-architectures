package com.fazv.data.datasource.remotedata

import com.fazv.data.api.CoinMasterApi
import com.fazv.data.mapper.CurrenciesListMapper
import com.fazv.data.mapper.CurrencyMapper
import com.fazv.domain.entities.CurrenciesListDTO
import com.fazv.utils.extension.makeRequest
import io.reactivex.Single

class RemoteDataSourceImpl(
    private val serviceApi: CoinMasterApi
) : RemoteDataSource {
    override fun getCurrenciesList(): Single<CurrenciesListDTO> =
        makeRequest {
            serviceApi
                .getCurrenciesList()
                .map {
                    CurrenciesListMapper().map(it)
                }
        }

    override fun getCurrency(coin: String): Single<CurrenciesListDTO> =
        makeRequest {
            serviceApi
                .getCurrency(coin)
                .map {
                    CurrencyMapper().map(it)
                }
        }
}