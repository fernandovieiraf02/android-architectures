package com.fazv.coinmaster.repository

import com.fazv.coinmaster.api.CoinMasterApi
import com.fazv.coinmaster.extension.makeRequest
import com.fazv.coinmaster.model.response.CurrenciesListResponse
import com.fazv.coinmaster.model.response.CurrencyResponse
import io.reactivex.Single

class CoinMasterRepository(private val serviceApi: CoinMasterApi) {
    fun getCurrenciesList(): Single<CurrenciesListResponse> =
        makeRequest {
            serviceApi.getCurrenciesList()
        }

    fun getCurrency(coin: String): Single<CurrencyResponse> =
        makeRequest {
            serviceApi.getCurrency(coin)
        }
}