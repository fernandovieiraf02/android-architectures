package com.fazv.data.mapper

import com.fazv.data.model.entities.CurrenciesListCache
import com.fazv.domain.entities.CurrenciesListDTO

class CurrenciesListCacheMapper {

    fun map(currenciesListCache: CurrenciesListCache): CurrenciesListDTO = currenciesListCache
        .run {
            CurrenciesListDTO(
                currencies = this.currencies
            )
        }

    fun map(currenciesListDTO: CurrenciesListDTO): CurrenciesListCache = currenciesListDTO
        .run {
            CurrenciesListCache(
                currencies = this.currencies
            )
        }
}