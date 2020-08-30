package com.fazv.coinmaster.model.mapper

import com.fazv.coinmaster.model.response.CurrencyResponse
import com.fazv.coinmaster.model.vo.CurrenciesListVO

class CurrencyMapper(): Mapper<CurrencyResponse, CurrenciesListVO> {
    override fun map(from: CurrencyResponse): CurrenciesListVO {
        with(from) {
            return CurrenciesListVO(quotes)
        }
    }
}