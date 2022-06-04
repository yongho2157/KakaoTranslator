package com.yh.kakaotranslator.data.repo

import com.yh.kakaotranslator.data.api.KakaoTranslatorDto
import com.yh.kakaotranslator.data.source.KakaoRemoteDataSource
import com.yh.kakaotranslator.util.Result
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {

    override fun getText(
        query: String,
        srcLang: String,
        targetLang: String
    ): Result<KakaoTranslatorDto> =
        kakaoRemoteDataSource.getText(query, srcLang, targetLang)

}