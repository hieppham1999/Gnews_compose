package com.hieppt.enrich.gnew.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hieppt.enrich.gnew.data.model.Article
import com.hieppt.enrich.gnew.data.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT data FROM article")
    fun getArticles(): Flow<List<Article>>

    @Insert(entity = ArticleEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Query("DELETE FROM article")
    suspend fun deleteAll()

}