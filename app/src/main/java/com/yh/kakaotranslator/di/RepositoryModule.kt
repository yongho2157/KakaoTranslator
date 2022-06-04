package com.yh.kakaotranslator.di

import com.example.repo.kakao.KakaoRepository
import com.example.repo.kakao.KakaoRepositoryImpl
import com.example.source_remote.kakao.KakaoRemoteDataSource
import com.example.source_remote.kakao.KakaoRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindKakaoRepository(): KakaoRepository =
        KakaoRepositoryImpl.getInstance(bindKakaoRepoDatasource())

    @Provides
    @Singleton
    fun bindKakaoRepoDatasource(): KakaoRemoteDataSource =
        KakaoRemoteDataSourceImpl.getInstance()

}