package com.yh.kakaotranslator.di

import com.yh.kakaotranslator.data.repo.KakaoRepository
import com.yh.kakaotranslator.data.repo.KakaoRepositoryImpl
import com.yh.kakaotranslator.data.source.KakaoRemoteDataSource
import com.yh.kakaotranslator.data.source.KakaoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindKakaoRepository(kakaoRepositoryImpl: KakaoRepositoryImpl): KakaoRepository

    @Singleton
    @Binds
    abstract fun bindKakaoRemoteDataSource(kakaoRemoteDataSourceImpl: KakaoRemoteDataSourceImpl): KakaoRemoteDataSource


}