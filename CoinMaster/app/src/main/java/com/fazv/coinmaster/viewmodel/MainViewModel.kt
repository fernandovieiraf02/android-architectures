package com.fazv.coinmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fazv.data.model.StateError
import com.fazv.data.model.StateLoading
import com.fazv.data.model.StateResponse
import com.fazv.data.model.StateSuccess
import com.fazv.domain.usecase.CalculateTargetValueUseCase
import com.fazv.domain.usecase.CalculateTargetValueUseCase.Params
import com.fazv.domain.usecase.GetCurrencyUseCase
import com.fazv.coinmaster.utils.ConstantUtils.Companion.EMPTY_STRING
import com.fazv.domain.entities.CurrenciesListDTO
import java.lang.Exception

class MainViewModel(
    private val currencyUseCase: GetCurrencyUseCase,
    private val calculateTargetValueUseCase: CalculateTargetValueUseCase
) : BaseViewModel() {

    private var isSourceCoin = true
    private var lastCoin: String = EMPTY_STRING

    var lastSourceInitial = MONEY_FLAG
    var lastTargetInitial = MONEY_FLAG
    var lastSourceValue: Double = 0.0

    private var sourceRate: Double = 0.0
    private var targetRate: Double = 0.0

    val currencyLiveData: LiveData<StateResponse<CurrenciesListDTO>>
        get() = _currencyLiveData
    private val _currencyLiveData = MutableLiveData<StateResponse<CurrenciesListDTO>>()

    val targetValueLiveData: LiveData<Double>
        get() = _targetValueLiveData
    private val _targetValueLiveData = MutableLiveData<Double>()

    fun calculateTargetValue(sourceValue: Double) {
        lastSourceValue = sourceValue

        calculateTargetValueUseCase
            .execute(param = Params(lastSourceValue, targetRate, sourceRate))
            .subscribe { result: Double ->
                _targetValueLiveData.postValue(result)
            }
            .also {
                disposables.addAll(it)
            }
    }

    fun getCurrency(coinSelected: String? = null, isSourceCoin: Boolean? = null) {
        setValues(isSourceCoin, coinSelected)

        currencyUseCase
            .execute(lastCoin)
            .doOnSubscribe { _currencyLiveData.postValue(StateLoading()) }
            .subscribe(
                {
                    onSuccess(it, this.isSourceCoin)
                },
                {
                    onError(it)
                }
            )
            .also { disposables.addAll(it) }
    }

    private fun setValues(isSourceCoin: Boolean?, coinSelected: String?) {
        this.isSourceCoin = isSourceCoin ?: this.isSourceCoin
        lastCoin = coinSelected ?: this.lastCoin
        if (this.isSourceCoin) {
            lastSourceInitial = lastCoin
        } else {
            lastTargetInitial = lastCoin
        }
    }

    private fun onError(error: Throwable) {
        _currencyLiveData.postValue(StateError(error))
    }

    private fun onSuccess(
        data: CurrenciesListDTO,
        isSourceCoin: Boolean
    ) {
        _currencyLiveData.postValue(StateSuccess(data))
        if (isSourceCoin) {
            setSourceRate(data)
        } else {
            setTargetRate(data)
        }
    }

    private fun setTargetRate(data: CurrenciesListDTO) {
        data.currencies.values
            .firstOrNull()
            ?.let { value ->
                try {
                    targetRate = value.toDouble()
                } catch (exception: Exception) {
                    _currencyLiveData.postValue(StateError(exception))
                }
            }
    }

    private fun setSourceRate(data: CurrenciesListDTO) {
        data.currencies.values
            .firstOrNull()
            ?.let { value ->
                try {
                    sourceRate = value.toDouble()
                } catch (exception: Exception) {
                    _currencyLiveData.postValue(StateError(exception))
                }
            }
    }

    companion object {
        const val MONEY_FLAG = "$"
    }
}