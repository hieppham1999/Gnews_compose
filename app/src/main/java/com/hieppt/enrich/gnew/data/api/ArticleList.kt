package com.hieppt.enrich.gnew.data.api

import com.google.gson.annotations.SerializedName
import com.hieppt.enrich.gnew.data.model.Article

data class ArticleList(
    @field:SerializedName("totalArticles") val totalArticles: Int = 0,
    @field:SerializedName("articles") val articles: List<Article> = listOf(),

    )
