package com.hieppt.enrich.gnew.data

import com.google.gson.annotations.SerializedName

data class Article(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("content") val content: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("publishedAt") val publishedAt: List<String>,
    @field:SerializedName("source") val source: ArticleSource,
)