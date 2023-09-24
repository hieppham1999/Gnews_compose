package com.hieppt.enrich.gnew.ui.theme.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.data.api.ArticleList
import com.hieppt.enrich.gnew.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private  val _repository: ArticleRepository): ViewModel() {

    @OptIn(ExperimentalFoundationApi::class)
    private val _screenState = MutableStateFlow(HomeScreenData(
        category = NewsCategory.business,
        pagerState = MutableStateFlow(
            PagerState(0)
        ),
    ))

    val screenState = _screenState.asStateFlow()


    init {
        updateHeadlines()
    }
    @OptIn(ExperimentalFoundationApi::class)
    fun updateHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            val newHeadlines = getHighLightArticles(_screenState.value.category)
            if (newHeadlines != null) {
                val list: MutableList<Article> = ArrayList()
                list.addAll(newHeadlines.articles)
                _screenState.update { state ->
                    if (list.size < 10) {
                        _repository.getArticleFromDb().distinctUntilChanged().collect() {
                            list.addAll(it)
                        }
                    }
                    state.copy(headlines = list)
                }
            }

        }
    }


    private suspend fun getHighLightArticles(category: NewsCategory): ArticleList? {
        val result = _repository.getTopHeadline(category = category)
        if (result.isSuccess()) {
            if (result.data != null) {
                return result.data
            }
        }
        return null

}

data class HomeScreenData @OptIn(ExperimentalFoundationApi::class) constructor(
    val category: NewsCategory,
    val headlines: List<Article>? = null,
    val pagerState: MutableStateFlow<PagerState>,
    val verticalRecommendArticleList: List<Article>? = null,
    val horizontalRecommendArticleList: List<Article>? = null,
)}