package com.hieppt.enrich.gnew.data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class ArticleSource(
    @field:SerializedName("name") val name:String,
    @field:SerializedName("url")val url: String
)
