package com.picpay.desafio.android.di

import com.picpay.desafio.android.api.RetrofitConfig
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.repository.Repository
import com.picpay.desafio.android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {
    val viewModels = module {
        viewModel { HomeViewModel(get()) }
    }
    val useCases = module {
        factory { GetUsersUseCase(get()) }
    }
    val services = module {
        factory { RetrofitConfig.createService() }
    }
    val repositories = module {
        factory { Repository(get()) }
    }
}