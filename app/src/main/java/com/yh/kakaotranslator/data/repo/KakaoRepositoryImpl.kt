package com.yh.kakaotranslator.data.repo

import com.yh.kakaotranslator.data.api.KakaoTranslatorDto
import com.yh.kakaotranslator.data.source.KakaoRemoteDataSource
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val kakaoRemoteDataSource: KakaoRemoteDataSource): KakaoRemoteDataSource {
    override fun getText(
        query: String,
        srcLang: String,
        targetLang: String,
        onSuccess: (KakaoTranslatorDto) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        kakaoRemoteDataSource.getText(query, srcLang, targetLang, onSuccess, onFailure)
    }


}