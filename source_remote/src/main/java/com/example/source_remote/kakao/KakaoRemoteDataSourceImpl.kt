package com.example.source_remote.kakao

import com.example.core.Result
import com.example.core.RetrofitClient
import com.example.response.remote.KakaoTranslatorDto
import com.example.source_remote.kakao.api.KakaoService

class KakaoRemoteDataSourceImpl : KakaoRemoteDataSource {

    private val kakaoServie = RetrofitClient.create<KakaoService>(KakaoService.BASE_URL)

    override fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto> {
        return try {
            val result =
                kakaoServie.getLanguage(query = query, srcLang = srcLang, targetLang = targetLang)
                    .execute().body()
            Result.Success(result!!)
        } catch (e: Exception) {
            Result.Error(Exception("에러가 발생."))
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: KakaoRemoteDataSourceImpl? = null

        fun getInstance(): KakaoRemoteDataSource =
            INSTANCE ?: synchronized(this) {
                KakaoRemoteDataSourceImpl().also {
                    INSTANCE = it
                }
            }
    }

}