package com.fazv.data.datasource.remotedata

import com.fazv.data.model.response.CurrenciesListResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getCurrenciesList(): Single<CurrenciesListResponse>
    fun getCurrency(coin: String): Single<CurrenciesListResponse>
}