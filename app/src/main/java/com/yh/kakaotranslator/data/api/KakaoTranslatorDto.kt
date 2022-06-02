package com.yh.kakaotranslator.data.api

data class KakaoTranslatorDto(
    val translatedText: List<List<String>>
)

data class TranslatedText (
    val text: String
)
