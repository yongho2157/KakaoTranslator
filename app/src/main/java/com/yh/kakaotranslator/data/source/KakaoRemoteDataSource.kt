package com.yh.kakaotranslator.data.source

import com.yh.kakaotranslator.data.api.KakaoTranslatorDto

interface KakaoRemoteDataSource {
    fun getText(
        query: String,
        srcLang: String,
        targetLang: String,
        onSuccess: (KakaoTranslatorDto) -> Unit,
        onFailure: (String) -> Unit
    )
}