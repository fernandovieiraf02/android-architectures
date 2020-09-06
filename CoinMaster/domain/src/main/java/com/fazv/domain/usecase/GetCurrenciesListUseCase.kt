package com.fazv.domain.usecase

import com.fazv.domain.entities.CurrenciesListDTO
import com.fazv.domain.repository.CurrencyRepository
import io.reactivex.Single

class GetCurrenciesListUseCase(
    private val repository: CurrencyRepository
) : UseCase<Unit, Single<CurrenciesListDTO>> {

    override fun execute(param: Unit): Single<CurrenciesListDTO> =
        repository.getCurrenciesList(false)
}