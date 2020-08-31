package com.fazv.domain.usecase

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