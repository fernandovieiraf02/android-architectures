package com.fazv.data.repositoryimpl

import com.fazv.data.api.CoinMasterApi
import com.fazv.data.mapper.CurrenciesListMapper
import com.fazv.data.mapper.CurrencyMapper
import com.fazv.domain.entities.CurrenciesListDTO
import com.fazv.domain.repository.CurrencyRepository
import com.fazv.utils.extension.makeRequest
import io.reactivex.Single

class CoinMasterRepositoryImpl(
    private val serviceApi: CoinMasterApi
) : CurrencyRepository {

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