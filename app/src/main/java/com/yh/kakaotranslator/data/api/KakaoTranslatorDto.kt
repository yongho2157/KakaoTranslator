package com.yh.kakaotranslator.data.api

import com.google.gson.annotations.SerializedName

data class KakaoTranslatorDto(
    @SerializedName("translated_text")
    val translated_text: List<List<String>>
)
