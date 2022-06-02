package com.yh.kakaotranslator.data.repo

import com.yh.kakaotranslator.data.api.KakaoTranslatorDto
import com.yh.kakaotranslator.util.Result

interface KakaoRepository {
    suspend fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto>
}