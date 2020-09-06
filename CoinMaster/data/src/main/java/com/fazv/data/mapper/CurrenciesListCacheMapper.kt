package com.fazv.data.mapper

import com.fazv.data.model.entities.CurrenciesListCache
import com.fazv.domain.entities.CurrenciesListDTO

class CurrenciesListCacheMapper {

    fun map(currenciesListCache: List<CurrenciesListCache>): List<CurrenciesListDTO> =
        currenciesListCache.map { map(it) }

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