package com.hieppt.enrich.gnew.data.repository

import com.hieppt.enrich.gnew.data.model.Article
import com.hieppt.enrich.gnew.data.model.NewsCategory
import com.hieppt.enrich.gnew.data.api.Resource
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getTopHeadline(category: NewsCategory, forceRefresh: Boolean = false): Resource<List<Article>>
    suspend fun getArticleFromDb(): Flow<List<Article>>
    suspend fun insertArticleToDB(article: Article)
    suspend fun deleteAllArticleFromDb()
}