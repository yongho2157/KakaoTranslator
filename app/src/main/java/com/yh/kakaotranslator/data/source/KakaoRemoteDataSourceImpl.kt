package com.yh.kakaotranslator.data.source

import com.yh.kakaotranslator.data.api.KakaoService
import com.yh.kakaotranslator.data.api.KakaoTranslatorDto
import com.yh.kakaotranslator.util.Result
import com.yh.kakaotranslator.util.RetrofitClient
import javax.inject.Inject

class KakaoRemoteDataSourceImpl @Inject constructor() : KakaoRemoteDataSource {

    private val kakaoServie = RetrofitClient.create<KakaoService>(KakaoService.BASE_URL)

    override fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto> {
        return try {
            val result =
                kakaoServie.getLanguage(query = query, srcLang = srcLang, targetLang = targetLang)
                    .execute().body()
            Result.Success(result!!)
        } catch (e: Exception) {
            Result.Error(Exception("에러가 발생."))
        }
    }
}