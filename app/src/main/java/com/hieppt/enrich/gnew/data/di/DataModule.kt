package com.hieppt.enrich.gnew.data.di

import com.hieppt.enrich.gnew.data.api.ArticleApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
//    @Singleton
//    @Provides
//    fun createGNewApi() : ArticleApiService {
//        return ArticleApiService.create()
//    }
}