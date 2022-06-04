package com.example.repo.kakao

import com.example.core.Result
import com.example.response.remote.KakaoTranslatorDto

interface KakaoRepository {
    fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto>
}