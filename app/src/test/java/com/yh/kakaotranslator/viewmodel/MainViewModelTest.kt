package com.yh.kakaotranslator.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.yh.kakaotranslator.MainViewModel
import com.yh.kakaotranslator.MainViewState
import com.yh.kakaotranslator.data.repo.KakaoRepository
import com.yh.kakaotranslator.data.source.KakaoRemoteDataSourceImplTest.Companion.mockTranslatorDto
import com.yh.kakaotranslator.util.Result
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
    private val mockObserver: Observer<MainViewState> = mock()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        mainViewModel = MainViewModel(mockKakaoRepository)
        mainViewModel.mainViewStateLiveData.observeForever(mockObserver)

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