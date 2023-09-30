package com.hieppt.enrich.gnew.data.di

import android.content.Context
import androidx.room.Room
import com.hieppt.enrich.gnew.data.api.ArticleApiService
import com.hieppt.enrich.gnew.data.api.LocalUserService
import com.hieppt.enrich.gnew.data.dao.ArticleDao
import com.hieppt.enrich.gnew.data.db.AppDatabase
import com.hieppt.enrich.gnew.data.repository.ArticleRepository
import com.hieppt.enrich.gnew.data.repository.ArticleRepositoryImpl
import com.hieppt.enrich.gnew.data.repository.UserRepository
import com.hieppt.enrich.gnew.data.repository.LocalUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindArticleRepo(
        articleRepository: ArticleRepositoryImpl
    ) : ArticleRepository

    @Binds
    @Singleton
    fun bindUserRepo(
        userRepository: LocalUserRepository
    ) : UserRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao
            = appDatabase.articleDao()
    @Singleton
    @Provides
    fun createGNewApi() : ArticleApiService {
        return ArticleApiService.create()
    }

    @Singleton
    @Provides
    fun createLocalUserService() : LocalUserService {
        return LocalUserService()
    }
}