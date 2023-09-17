package com.hieppt.enrich.gnew.ui.theme.screens.home

import androidx.lifecycle.ViewModel
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.data.api.ArticleList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

}

data class HomeScreenData(val category: NewsCategory,
    val highlightNews: ArticleList,
    val verticalRecommendArticleList: ArticleList,
    val horizontalReCommendArticleList: ArticleList
)