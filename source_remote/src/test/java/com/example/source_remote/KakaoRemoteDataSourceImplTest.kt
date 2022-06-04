package com.example.source_remote

import com.example.core.Result
import com.example.response.remote.KakaoTranslatorDto
import com.example.source_remote.kakao.KakaoRemoteDataSource
import com.example.source_remote.kakao.KakaoRemoteDataSourceImpl
import com.example.source_remote.kakao.api.KakaoService
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okio.Timeout
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.response.remote.mock.mockTranslatorDto

class KakaoRemoteDataSourceImplTest {

    private lateinit var remoteDataSource: KakaoRemoteDataSource
    private val kakaoService: KakaoService = mock()

    @Before
    fun setUp() {
        initMockTextApi()
        remoteDataSource = KakaoRemoteDataSourceImpl()
    }

    @Test
    fun getTextSuccessTest() = runBlocking {
        assertEquals((remoteDataSource.getText(query = "안드로이드", srcLang = "kr", targetLang = "en") as Result.Success).data, mockTranslatorDto)

    }

    @Test
    fun getTextFailureTest() {
        val failResult = Result.Error(Exception("에러가 발생."))

        Mockito.`when`(kakaoService.getLanguage(query = "안드로이드", srcLang = "kr", targetLang = "en")).then { failResult }
        assertEquals((remoteDataSource.getText(query = "안드", srcLang = "k", targetLang = "e") as Result.Error).exception.message, failResult.exception.message)
    }

    private fun initMockTextApi() {
        val call = object : Call<KakaoTranslatorDto> {
            override fun execute(): Response<KakaoTranslatorDto> {
                return Response.success(mockTranslatorDto)
            }

            override fun clone(): Call<KakaoTranslatorDto> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<KakaoTranslatorDto>) {
                TODO("Not yet implemented")
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }

        }
        Mockito.`when`(kakaoService.getLanguage(query = "안드로이드", srcLang = "kr", targetLang = "en")).thenReturn(call)
    }
}