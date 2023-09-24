package com.hieppt.enrich.gnew.data.repository

import android.util.Log
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.data.api.ArticleApiService
import com.hieppt.enrich.gnew.data.api.ArticleList
import com.hieppt.enrich.gnew.data.api.Resource
import com.hieppt.enrich.gnew.data.dao.ArticleDao
import com.hieppt.enrich.gnew.data.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val service: ArticleApiService, private val db: ArticleDao) :
    ArticleRepository {
    override suspend fun getTopHeadline(category: NewsCategory): Resource<ArticleList> {
        return try {
            service.getTopHeadlines(category = category.name)

        } catch (e: Exception) {
            Log.e("getTopHeadline", e.toString())
            Resource.error(msg = null, data = null)
        }
    }

    override suspend fun getArticleFromDb(): Flow<List<Article>> = db.getArticles()

    override suspend fun insertArticleToDB(article: Article) = db.insert(ArticleEntity(data = article))

    override suspend fun deleteAllArticleFromDb() = db.deleteAll()


}

