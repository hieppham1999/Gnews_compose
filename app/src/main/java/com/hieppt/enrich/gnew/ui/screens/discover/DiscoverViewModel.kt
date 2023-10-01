package com.hieppt.enrich.gnew.ui.screens.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.helper.fromJsonList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor (savedStateHandle: SavedStateHandle) : ViewModel() {
    private val articlesData: String? = savedStateHandle["dataString"]

    val list = mutableListOf<Article>()

    init {
        getArticleData()
    }

    private fun getArticleData() {
        val parsedArticles = articlesData?.let { Gson().fromJsonList<Article>(it) }
        if (parsedArticles != null) {
            list.clear()
            list.addAll(parsedArticles)
        }
    }
}