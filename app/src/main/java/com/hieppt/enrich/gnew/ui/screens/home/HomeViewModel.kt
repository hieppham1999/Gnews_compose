package com.hieppt.enrich.gnew.ui.screens.home

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.data.User
import com.hieppt.enrich.gnew.data.api.ArticleList
import com.hieppt.enrich.gnew.data.repository.ArticleRepository
import com.hieppt.enrich.gnew.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val _repository: ArticleRepository,
    private val _userRepository: UserRepository,
) : ViewModel() {

    @OptIn(ExperimentalFoundationApi::class)
    private val _screenState = MutableStateFlow(
        HomeScreenData(
            category = NewsCategory.business,
            pagerState = MutableStateFlow(
                PagerState(0)
            ),
        )
    )

    val screenState = _screenState.asStateFlow()

    init {
        updateHeadlines()
        getUser()
        loadingUserAvatar()
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun updateHeadlines() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val newHeadlines = getHighLightArticles(_screenState.value.category)
//            if (newHeadlines != null) {
//                val list: MutableList<Article> = ArrayList()
//                newHeadlines.articles.forEach { _repository.insertArticleToDB(it) }
//                list.addAll(newHeadlines.articles)
//                _screenState.update { state ->
//                    if (list.size < 10) {
//                        _repository.getArticleFromDb().distinctUntilChanged().collect() {
//                            list.addAll(it)
//                        }
//                    }
//                    state.copy(headlines = list)
//                }
//            }
//
//        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun updateUserAvatar(imgBitmap: Bitmap) {
        _screenState.update { state ->
            state.copy(avatar = imgBitmap)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun getUser() {
        viewModelScope.launch {
            val user = _userRepository.getUser()
            _screenState.update { data ->
                data.copy(user = user)
            }
        }

    }

    fun loadingUserAvatar() {
        viewModelScope.launch {
            val imgBitmap = _userRepository.getImage(path = _screenState.value.user?.avatar)
            imgBitmap?.let { updateUserAvatar(imgBitmap = it) }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun updateCategoryTab(category: NewsCategory) {
        _screenState.update { state ->
            state.copy(category = category)
        }

        updateHeadlines()
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
        val user: User? = null,
        val avatar: Bitmap? = null,
        val headlines: List<Article>? = null,
        val pagerState: MutableStateFlow<PagerState>,
        val verticalRecommendArticleList: List<Article>? = null,
        val horizontalRecommendArticleList: List<Article>? = null,
    )
}