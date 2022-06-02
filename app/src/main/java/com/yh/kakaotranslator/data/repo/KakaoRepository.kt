package com.yh.kakaotranslator.data.repo

import com.yh.kakaotranslator.data.api.KakaoTranslatorDto

interface KakaoRepository {
    fun getText(
        query: String,
        srcLang: String,
        targetLang: String,
        onSuccess: (KakaoTranslatorDto) -> Unit,
        onFailure: (String) -> Unit
    )
}