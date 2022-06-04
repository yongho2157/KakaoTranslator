package com.yh.kakaotranslator.data.repo

import com.nhaarman.mockitokotlin2.mock
import com.yh.kakaotranslator.data.source.KakaoRemoteDataSource
import com.yh.kakaotranslator.data.source.KakaoRemoteDataSourceImplTest.Companion.mockTranslatorDto
import com.yh.kakaotranslator.util.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class KakaoRepositoryImplTest {

    private lateinit var repository: KakaoRepository

    private val kakaoRemoteDataSource: KakaoRemoteDataSource = mock()

    @Before
    fun setUp() {
        repository = KakaoRepositoryImpl(kakaoRemoteDataSource)
    }

    @Test
    fun getTextSuccessTest() {
        Mockito.`when`(kakaoRemoteDataSource.getText(query = "안드로이드", srcLang = "kr", targetLang = "en")).thenReturn(Result.Success(mockTranslatorDto))
    }

    @Test
    fun getTextFailureTest() {
        val failResult = Result.Error(Exception("에러가 발생."))
        Mockito.`when`(kakaoRemoteDataSource.getText(query = "안드로이드", srcLang = "kr", targetLang = "en")).then { failResult }

        Assert.assertEquals(
            (repository.getText(query = "안드로이드", srcLang = "kr", targetLang = "en")) as Result.Error,
        kakaoRemoteDataSource.getText(query = "안드로이드", srcLang = "kr", targetLang = "en")
        )

    }

}