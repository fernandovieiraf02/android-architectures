package com.fazv.data.mapper

import com.fazv.data.model.response.CurrenciesListResponse
import com.fazv.domain.entities.CurrenciesListDTO

class CurrenciesListMapper : Mapper<CurrenciesListResponse, CurrenciesListDTO> {
    override fun map(from: CurrenciesListResponse): CurrenciesListDTO {
        with(from) {
            return CurrenciesListDTO(currencies)
        }
    }
}