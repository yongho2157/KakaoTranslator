package com.yh.kakaotranslator.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.Result
import com.example.repo.kakao.KakaoRepository
import com.example.response.remote.mock.mockTranslatorDto
import com.nhaarman.mockitokotlin2.mock
import com.yh.kakaotranslator.MainViewState
import com.yh.kakaotranslator.base.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockKakaoRepository: KakaoRepository = mock()
    private val mockObserver: Observer<ViewState> = mock()
    private val application: Application = mock()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        mainViewModel = MainViewModel(application, mockKakaoRepository)
        mainViewModel.viewStateLiveData.observeForever(mockObserver)

        mainViewModel.langObservableField.set("en")
        mainViewModel.inputTextLiveData.value = "android"
    }


    @Test
    fun checkTranslateSuccessTest() = runBlocking {

        //given
        Mockito.`when`(
            mockKakaoRepository.getText(
                mainViewModel.inputTextLiveData.value ?: "",
                "kr",
                mainViewModel.langObservableField.get() ?: ""
            )
        ).thenReturn(Result.Success(mockTranslatorDto))

        //when
        mainViewModel.translate()

        delay(100)

        //then
        Mockito.verify(mockObserver)
            .onChanged(MainViewState.GetText(mockTranslatorDto.translated_text))
    }


    @Test
    fun checkTranslateFailureTest() = runBlocking {

        //given
        Mockito.`when`(
            mockKakaoRepository.getText(
                mainViewModel.inputTextLiveData.value ?: "",
                "kr",
                mainViewModel.langObservableField.get() ?: ""
            )
        ).then {
            Result.Error(Exception("에러가 발생."))
        }

        //when
        mainViewModel.translate()

        delay(100)

        //then
        Mockito.verify(mockObserver)
            .onChanged(MainViewState.Error("에러가 발생."))
    }


    @After
    fun tearDown() {

    }
}