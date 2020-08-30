package com.fazv.coinmaster.usecase

import com.fazv.coinmaster.model.mapper.Mapper
import com.fazv.coinmaster.model.response.CurrenciesListResponse
import com.fazv.coinmaster.model.vo.CurrenciesListVO
import com.fazv.coinmaster.repository.CoinMasterRepository
import io.reactivex.Single

class GetCurrenciesListUseCase(
    private val repository: CoinMasterRepository,
    private val mapper: Mapper<CurrenciesListResponse, CurrenciesListVO>
) : UseCase<Unit, Single<CurrenciesListVO>> {

    override fun execute(param: Unit): Single<CurrenciesListVO> =
        repository
            .getCurrenciesList()
            .map {
                mapper.map(it)
            }
}