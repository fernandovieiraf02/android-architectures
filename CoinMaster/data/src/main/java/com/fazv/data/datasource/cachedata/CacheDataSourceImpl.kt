package com.fazv.data.datasource.cachedata

import com.fazv.data.mapper.CurrenciesListCacheMapper
import com.fazv.domain.entities.CurrenciesListDTO
import io.reactivex.Single

class CacheDataSourceImpl(
    private val currenciesListDAO: CurrenciesListDAO
) : CacheDataSource {
    override fun insert(currenciesListDTO: CurrenciesListDTO) {
        CurrenciesListCacheMapper()
            .map(currenciesListDTO)
            .let { cache ->
                currenciesListDAO.insert(cache)
            }
    }

    override fun updateData(currenciesListDTO: CurrenciesListDTO) {
        CurrenciesListCacheMapper()
            .map(currenciesListDTO)
            .let { cache ->
                currenciesListDAO.updateData(cache)
            }
    }

    override fun getCurrenciesList(): Single<CurrenciesListDTO> =
        currenciesListDAO
            .getCurrenciesList()
            .map {
                CurrenciesListCacheMapper().map(it)
            }
}