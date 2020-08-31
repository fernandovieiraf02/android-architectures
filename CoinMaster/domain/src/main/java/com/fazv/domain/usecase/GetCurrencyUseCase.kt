package com.fazv.domain.usecase

import com.fazv.coinmaster.model.mapper.Mapper
import com.fazv.coinmaster.model.response.CurrencyResponse
import com.fazv.coinmaster.model.vo.CurrenciesListVO
import com.fazv.coinmaster.repository.CoinMasterRepository
import io.reactivex.Single

class GetCurrencyUseCase(
    private val repository: CoinMasterRepository,
    private val mapper: Mapper<CurrencyResponse, CurrenciesListVO>
) : UseCase<String, Single<CurrenciesListVO>> {

    override fun execute(coin: String): Single<CurrenciesListVO> =
        repository
            .getCurrency(coin)
            .map {
                mapper.map(it)
            }
}