package com.hieppt.enrich.gnew.ui.screens.home

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.ui.screens.common.ArticleHorizontalCard
import com.hieppt.enrich.gnew.ui.screens.common.ArticleVerticalCard
import com.hieppt.enrich.gnew.ui.screens.common.HeaderWithTextButton
import com.hieppt.enrich.gnew.ui.screens.home.compose.CategorySliderCard
import com.hieppt.enrich.gnew.ui.screens.home.compose.UserGreeting
import com.hieppt.enrich.gnew.ui.theme.backgroundColor
import com.hieppt.enrich.gnew.ui.theme.tabBackgroundColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onItemClick: (article: Article?) -> Unit
) {

    val screenState by viewModel.screenState.collectAsState()

    val currentCategoryIndex = NewsCategory.values().indexOf(screenState.category)

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, currentCategoryIndex)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        UserGreeting(userName = "Hiep")
        CategorySliderCard(
            listItem = screenState.headlines?.subList(0, 4),
            header = screenState.category.displayName,
            onClick = {}
        )

        ScrollableTabRow(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(top = 20.dp),
            divider = {},
            selectedTabIndex = screenState.category.ordinal, containerColor = tabBackgroundColor,
            indicator = indicator
        ) {
            NewsCategory.values().forEachIndexed { index, category ->
                Tab(modifier = Modifier.zIndex(6f), text = { Text(text = category.displayName) },
                    selected = NewsCategory.values().indexOf(category) == index,
                    onClick = { viewModel.updateCategoryTab(category) }
                )
            }
        }

        HeaderWithTextButton(onClick = {})

        ArticleVerticalCard(article = screenState.headlines?.get(0), onClick = { onItemClick(screenState.headlines?.get(0))})

        ArticleHorizontalCard(article = screenState.headlines?.get(0), onClick = {
            onItemClick(screenState.headlines?.get(0))
        })

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, currentPage: Int) {
    val transition = updateTransition(currentPage)
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(bottom = 5.dp)
            .fillMaxSize()
            .background(color = backgroundColor, RoundedCornerShape(bottomStartPercent = 25, bottomEndPercent = 25))
//            .border(BorderStroke(2.dp, Color(0xFFC13D25)), RoundedCornerShape(50))
            .zIndex(1f)
    )
}