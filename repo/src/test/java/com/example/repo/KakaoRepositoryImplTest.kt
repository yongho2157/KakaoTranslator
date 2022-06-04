package com.example.repo

import com.example.core.Result
import com.example.repo.kakao.KakaoRepository
import com.example.repo.kakao.KakaoRepositoryImpl
import com.example.response.remote.mock.mockTranslatorDto
import com.example.source_remote.kakao.KakaoRemoteDataSource
import com.nhaarman.mockitokotlin2.mock
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
        Mockito.`when`(
            kakaoRemoteDataSource.getText(
                query = "안드로이드",
                srcLang = "kr",
                targetLang = "en"
            )
        ).thenReturn(Result.Success(mockTranslatorDto))

        Assert.assertEquals(
            ((repository.getText(
                query = "안드로이드",
                srcLang = "kr",
                targetLang = "en"
            )) as Result.Success).data.translated_text,
            (kakaoRemoteDataSource.getText(query = "안드로이드", srcLang = "kr", targetLang = "en") as Result.Success).data.translated_text
        )

    }

    @Test
    fun getTextFailureTest() {
        val failResult = Result.Error(Exception("에러가 발생."))
        Mockito.`when`(
            kakaoRemoteDataSource.getText(
                query = "안드로이드",
                srcLang = "kr",
                targetLang = "en"
            )
        ).then { failResult }

        Assert.assertEquals(
            (repository.getText(
                query = "안드로이드",
                srcLang = "kr",
                targetLang = "en"
            )) as Result.Error,
            kakaoRemoteDataSource.getText(query = "안드로이드", srcLang = "kr", targetLang = "en")
        )

    }

}