package com.hieppt.enrich.gnew.data.repository

import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.data.api.ArticleList
import com.hieppt.enrich.gnew.data.api.Resource
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getTopHeadline(category: NewsCategory): Resource<ArticleList>

    suspend fun getArticleFromDb(): Flow<List<Article>>
    suspend fun insertArticleToDB(article: Article)
    suspend fun deleteAllArticleFromDb()
}