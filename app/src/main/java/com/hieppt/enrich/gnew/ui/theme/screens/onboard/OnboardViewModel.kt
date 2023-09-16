package com.hieppt.enrich.gnew.ui.theme.screens.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import com.hieppt.enrich.gnew.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(): ViewModel(){
    @OptIn(ExperimentalFoundationApi::class)
    private val _pagerIndexState = MutableStateFlow(PagerState(0))

    val introData = listOf(
        OnboardIntroData(
            R.drawable.culinary.toString(),
            "Cooking is a caring and nurturing act.",
            "It's kind of the ultimate gift for someone, to cook for them"
        ),
        OnboardIntroData(
            R.drawable.discover.toString(),
            "Discover nature and explore beyond",
            "find with us your dream house quickly and precisely",
        ),
        OnboardIntroData(
            R.drawable.nature.toString(),
            "Look deep into nature",
            "And then you will understand everything better",
        ),
        OnboardIntroData(
            R.drawable.science.toString(),
            "We are drowning in information",
            "but starved for knowledge."
        )
    )
    @OptIn(ExperimentalFoundationApi::class)
    val currentPageIndex = _pagerIndexState.asStateFlow()
}

data class OnboardIntroData(
    val imageUrl : String,
    val title: String,
    val subtitle: String
)