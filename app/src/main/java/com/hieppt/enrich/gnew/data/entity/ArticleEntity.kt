package com.hieppt.enrich.gnew.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.ArticleSource
import javax.annotation.Nonnull

@Entity(tableName = "article")
data class ArticleEntity(
    @Nonnull @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val data: Article,
)


class EntityConverter {
    @TypeConverter
    fun articleToString(article: Article): String {
        return Gson().toJson(article)
    }

    @TypeConverter
    fun articleFromString(string: String): Article {
        return Gson().fromJson(string, Article::class.java)
    }

    @TypeConverter
    fun articleSourceToString(articleSource: ArticleSource): String {
        return Gson().toJson(articleSource)
    }

    @TypeConverter
    fun articleSourceFromString(string: String): ArticleSource {
        return Gson().fromJson(string, ArticleSource::class.java)
    }
}