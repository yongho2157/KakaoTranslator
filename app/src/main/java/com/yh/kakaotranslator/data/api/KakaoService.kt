package com.yh.kakaotranslator.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService {

    companion object {
        private const val API_KEY = "772f86112d2d9b538f98634873a727ee"
        const val BASE_URL = "https://dapi.kakao.com/"
    }

    @Headers("Authorization: KakaoAK $API_KEY")
    @GET("v2/translation/translate")
    fun getLanguage(
        @Query("query") query: String,
        @Query("src_lang") srcLang: String,
        @Query("target_lang") targetLang: String
    ): Call<KakaoTranslatorDto>

}