package com.picpay.desafio.android.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.domain.usecase.RxImmediateSchedulerRule
import com.picpay.desafio.android.model.ErrorEvent
import com.picpay.desafio.android.model.LoadingEvent
import com.picpay.desafio.android.model.SuccessEvent
import com.picpay.desafio.android.model.User
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import okhttp3.internal.wait
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Error

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    private val useCase = mock<GetUsersUseCase>()
    private val viewModel = HomeViewModel(useCase)
    private val expectedValueWithSuccess = listOf<User>(mock())
    private val expectedEmptyValue = emptyList<User>()
    private class TestException : Throwable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Should return a SuccessEvent when get user list`() {
        Mockito
            .`when`(useCase.execute())
            .thenReturn(Single.just(expectedValueWithSuccess))

        viewModel.fetchUsersList()

        assert(viewModel.listUsersProvider.value is SuccessEvent)
    }

    @Test
    fun `Should return a SuccessEvent when get a empty user list`() {
        Mockito
            .`when`(useCase.execute())
            .thenReturn(Single.just(expectedEmptyValue))

        viewModel.fetchUsersList()

        assert(viewModel.listUsersProvider.value is SuccessEvent)
    }

    @Test
    fun `Should return a ErrorEvent when get user list`() {
        Mockito
            .`when`(useCase.execute())
            .thenReturn(Single.error(TestException()))

        viewModel.fetchUsersList()

        assert(viewModel.listUsersProvider.value is ErrorEvent)
    }
}