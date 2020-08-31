package com.fazv.data.mapper

import com.fazv.data.model.response.CurrencyResponse
import com.fazv.domain.entities.CurrenciesListDTO

class CurrencyMapper(): Mapper<CurrencyResponse, CurrenciesListDTO> {
    override fun map(from: CurrencyResponse): CurrenciesListDTO {
        with(from) {
            return CurrenciesListDTO(quotes)
        }
    }
}