package com.fazv.domain.repository

import com.fazv.domain.entities.CurrenciesListDTO
import io.reactivex.Single

interface CurrencyRepository {
    fun getCurrenciesList(shouldUpdate: Boolean): Single<CurrenciesListDTO>
    fun getCurrency(coin: String): Single<CurrenciesListDTO>
}