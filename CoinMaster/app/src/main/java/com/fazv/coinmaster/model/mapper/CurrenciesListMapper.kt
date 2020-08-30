package com.fazv.coinmaster.model.mapper

import com.fazv.coinmaster.model.response.CurrenciesListResponse
import com.fazv.coinmaster.model.vo.CurrenciesListVO

class CurrenciesListMapper : Mapper<CurrenciesListResponse, CurrenciesListVO> {
    override fun map(from: CurrenciesListResponse): CurrenciesListVO {
        with(from) {
            return CurrenciesListVO(currencies)
        }
    }
}