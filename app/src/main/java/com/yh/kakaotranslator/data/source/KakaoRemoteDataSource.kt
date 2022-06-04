package com.yh.kakaotranslator.data.source

import com.yh.kakaotranslator.data.api.KakaoTranslatorDto
import com.yh.kakaotranslator.util.Result

interface KakaoRemoteDataSource {
    fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto>
}