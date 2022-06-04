package com.example.source_remote.kakao

import com.example.core.Result
import com.example.response.remote.KakaoTranslatorDto

interface KakaoRemoteDataSource {
    fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto>
}