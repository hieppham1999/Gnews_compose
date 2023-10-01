package com.hieppt.enrich.gnew.ui.screens.discover
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val _repository: ArticleRepository,
) : ViewModel() {
    private val categoryString: String? = savedStateHandle["category"]

    private val _state = MutableStateFlow(
        DiscoverScreenData(
            articleList = listOf()
        )
    )

    val screenState = _state.asStateFlow()

    init {
        getCategory()
        getArticleData()
    }

    private fun getCategory() {
        _state.update { state ->
            state.copy(
                category = if (categoryString != null) {
                    NewsCategory.valueOf(categoryString)
                } else {
                    NewsCategory.general
                }
            )
        }
    }

    private fun getArticleData() {
        viewModelScope.launch(Dispatchers.IO) {

            val newHeadlines = getHighLightArticles(_state.value.category)
            if (newHeadlines != null) {
                _state.update { state ->
                    state.copy(
                        articleList = newHeadlines
                    )

                }
            }

        }
    }

    private suspend fun getHighLightArticles(category: NewsCategory, forceRefresh: Boolean = false): List<Article>? {
        val result = _repository.getTopHeadline(category = category, forceRefresh = forceRefresh)
        if (result.isSuccess()) {
            if (result.data != null) {
                return result.data
            }
        }
        return null
    }
}

data class DiscoverScreenData constructor(
    val category: NewsCategory = NewsCategory.general,
    val articleList: List<Article>
)