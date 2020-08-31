package com.fazv.domain.repository

import com.fazv.domain.entities.CurrenciesListDTO
import io.reactivex.Single

interface CurrencyRepository {
    fun getCurrenciesList(): Single<CurrenciesListDTO>
    fun getCurrency(): Single<CurrenciesListDTO>
}