package com.fazv.data.datasource.remotedata

import com.fazv.domain.entities.CurrenciesListDTO
import io.reactivex.Single

interface RemoteDataSource {
    fun getCurrenciesList(): Single<CurrenciesListDTO>
    fun getCurrency(coin: String): Single<CurrenciesListDTO>
}