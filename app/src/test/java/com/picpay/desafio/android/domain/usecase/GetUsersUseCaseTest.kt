package com.picpay.desafio.android.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.extension.makeObservable
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.Repository
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUsersUseCaseTest {

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    private val repository = mock<Repository>()
    private val useCase = GetUsersUseCase(repository)
    private val expectedValue = listOf<User>(mock())
    private lateinit var testObservable: TestObserver<List<User>>
    private class TestException : Throwable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val mockedReturn = Single.just<List<User>>(expectedValue)
        testObservable = mockedReturn.test()
        Mockito
            .`when`(repository.getUsers())
            .thenReturn(mockedReturn)
    }

    @Test
    fun `Should load with success a user list`() {
        val result = useCase.execute().makeObservable()
        testObservable
            .assertNoErrors()
            .assertComplete()
            .assertValue(expectedValue)
        result.subscribe(testObservable)
    }

    @Test
    fun `Should load with error a user list`() {

        Mockito
            .`when`(repository.getUsers())
            .thenReturn(Single.error<List<User>>(TestException()))

        val result = useCase.execute().makeObservable()
        val testObserver = useCase.execute().test()

        testObserver
            .assertError(TestException::class.java)
            .assertNotComplete()

        result.subscribe(testObserver)
    }
}