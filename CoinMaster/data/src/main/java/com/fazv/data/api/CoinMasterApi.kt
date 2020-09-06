package com.fazv.data.api

import com.fazv.data.BuildConfig
import com.fazv.data.model.response.CurrenciesListResponse
import com.fazv.data.model.response.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinMasterApi {
    @GET(CURRENCY_ENDPOINT)
    fun getCurrency(@Query(QUERY_CURRENCIES) coin: String): Single<CurrencyResponse>

    @GET(LIST_ENDPOINT)
    fun getCurrenciesList(): Single<CurrenciesListResponse>

    companion object {
        private const val QUERY_ACCESS_KEY = "?access_key=${BuildConfig.CURRENCYLAYER_ACCESS_KEY}"
        private const val QUERY_CURRENCIES = "currencies"
        private const val CURRENCY_ENDPOINT = "live$QUERY_ACCESS_KEY"
        private const val LIST_ENDPOINT = "list$QUERY_ACCESS_KEY"
    }
}