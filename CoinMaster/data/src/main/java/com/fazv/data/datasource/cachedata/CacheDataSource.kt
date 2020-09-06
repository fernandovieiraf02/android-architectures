package com.fazv.data.datasource.cachedata

import com.fazv.domain.entities.CurrenciesListDTO
import io.reactivex.Single

interface CacheDataSource {
    fun insert(currenciesListDTO: CurrenciesListDTO)
    fun updateData(currenciesListDTO: CurrenciesListDTO)
    fun getCurrenciesList(): Single<List<CurrenciesListDTO>>
}