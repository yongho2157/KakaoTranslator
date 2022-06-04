package com.example.repo.kakao

import com.example.core.Result
import com.example.response.remote.KakaoTranslatorDto
import com.example.source_remote.kakao.KakaoRemoteDataSource

class KakaoRepositoryImpl(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {

    override fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto> =
        kakaoRemoteDataSource.getText(query, srcLang, targetLang)

    companion object {

        @Volatile
        private var INSTANCE: KakaoRepositoryImpl? = null

        fun getInstance(kakaoRemoteDataSource: KakaoRemoteDataSource): KakaoRepositoryImpl =
            INSTANCE ?: synchronized(this) {
                KakaoRepositoryImpl(kakaoRemoteDataSource).also {
                    INSTANCE = it
                }
            }
    }
}