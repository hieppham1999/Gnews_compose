package com.hieppt.enrich.gnew.data.repository

import android.util.Log
import com.hieppt.enrich.gnew.data.model.Article
import com.hieppt.enrich.gnew.data.model.NewsCategory
import com.hieppt.enrich.gnew.data.api.ArticleApiService
import com.hieppt.enrich.gnew.data.api.Resource
import com.hieppt.enrich.gnew.data.dao.ArticleDao
import com.hieppt.enrich.gnew.data.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val service: ArticleApiService, private val db: ArticleDao) :
    ArticleRepository {

    private val _articleMemCache = mutableMapOf<NewsCategory, List<Article>?>()

    override suspend fun getTopHeadline(category: NewsCategory, forceRefresh: Boolean): Resource<List<Article>> {
        return try {

            if (_articleMemCache.containsKey(category) && !forceRefresh) {
                println("GET ${category.name} DATA FROM CACHE!!!!")

                return Resource.success(data = _articleMemCache[category])
            }

            val result = service.getTopHeadlines(category = category.name)
            if (result.isSuccess()) {
                _articleMemCache.merge(category, result.data!!.articles) { _, newValue -> newValue }
                return Resource.success(data = result.data.articles)
            }
            Resource.error( msg = null, data = null)

        } catch (e: Exception) {
            Log.e("getTopHeadline", e.toString())
            Resource.error(msg = null, data = null)
        }
    }

    override suspend fun getArticleFromDb(): Flow<List<Article>> = db.getArticles()

    override suspend fun insertArticleToDB(article: Article) = db.insert(ArticleEntity(data = article))

    override suspend fun deleteAllArticleFromDb() = db.deleteAll()


}

