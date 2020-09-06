package com.fazv.domain.usecase

import com.fazv.domain.entities.CurrenciesListDTO
import com.fazv.domain.repository.CurrencyRepository
import io.reactivex.Single

class GetCurrencyUseCase(
    private val repository: CurrencyRepository
) : UseCase<String, Single<CurrenciesListDTO>> {

    override fun execute(coin: String): Single<CurrenciesListDTO> =
        repository.getCurrency(coin)
}