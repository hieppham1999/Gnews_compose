package com.hieppt.enrich.gnew.ui.screens.article_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hieppt.enrich.gnew.data.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ArticleDetailViewModel @Inject constructor (savedStateHandle: SavedStateHandle) : ViewModel() {
    private val articleData: String? = savedStateHandle["dataString"]

    @OptIn(ExperimentalFoundationApi::class)
    private val _screenState = MutableStateFlow(
        ArticleDetailScreenData(
            article = null,
            pagerState = MutableStateFlow(
                PagerState(0)
            ),
        )
    )

    val screenState = _screenState.asStateFlow()

    init {
        getArticleData()
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun getArticleData() {
        val parsedArticle = Gson().fromJson(articleData, Article::class.java)
        if (parsedArticle != null) {
            _screenState.update { state -> state.copy(article = parsedArticle) }
        }
    }
}

data class ArticleDetailScreenData @OptIn(ExperimentalFoundationApi::class) constructor(
    val article: Article?,
    val pagerState: MutableStateFlow<PagerState>,
)