package com.picpay.desafio.android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.extension.postError
import com.picpay.desafio.android.extension.postLoading
import com.picpay.desafio.android.extension.postSuccess
import com.picpay.desafio.android.model.EventData
import com.picpay.desafio.android.model.User
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val useCase: GetUsersUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val listUsersState = MutableLiveData<EventData<List<User>>>()
    val listUsersProvider: LiveData<EventData<List<User>>>
        get() = listUsersState

    fun fetchUsersList() {
        useCase
            .execute()
            .doOnSubscribe { listUsersState.postLoading() }
            .subscribe(
                ::onFetchUserListSuccessfully,
                ::onFetchUserListFail
            )
            .also { disposables.add(it) }
    }

    private fun onFetchUserListFail(error: Throwable) {
        listUsersState.postError(error)
    }

    private fun onFetchUserListSuccessfully(userList: List<User>) {
        listUsersState.postSuccess(userList)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}

