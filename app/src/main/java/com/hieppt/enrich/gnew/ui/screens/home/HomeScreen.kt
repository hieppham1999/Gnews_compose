package com.hieppt.enrich.gnew.ui.screens.home

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.material3.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.pullrefresh.pullRefresh
import androidx.compose.material3.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.data.NewsCategory
import com.hieppt.enrich.gnew.ui.screens.common.ArticleHorizontalCard
import com.hieppt.enrich.gnew.ui.screens.common.ArticleVerticalCard
import com.hieppt.enrich.gnew.ui.screens.common.CustomTabShape
import com.hieppt.enrich.gnew.ui.screens.common.HeaderWithTextButton
import com.hieppt.enrich.gnew.ui.screens.common.HighLightIndicatorShape
import com.hieppt.enrich.gnew.ui.screens.home.compose.CategorySliderCard
import com.hieppt.enrich.gnew.ui.screens.home.compose.UserGreeting
import com.hieppt.enrich.gnew.ui.theme.backgroundColor
import com.hieppt.enrich.gnew.ui.theme.highlightColor
import com.hieppt.enrich.gnew.ui.theme.tabBackgroundColor

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onItemClick: (article: Article?) -> Unit,
    onShowAllVerticalList: (category: NewsCategory) -> Unit,
    onShowAllHorizontalList: (category: NewsCategory) -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()

    val currentCategoryIndex = NewsCategory.values().indexOf(screenState.category)

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, currentCategoryIndex)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = screenState.isLoading,
        onRefresh = { viewModel.updateHeadlines(forceRefresh = true) },
        refreshThreshold = 35.dp
    )

    LaunchedEffect(key1 = null) {
        viewModel.loadingUserAvatar()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        UserGreeting(userName = screenState.user?.userName, imageBitmap = screenState.avatar)
        CategorySliderCard(
            modifier = Modifier
                .fillMaxHeight(0.25F),
            listItem = screenState.headlines?.subList(0, 4),
            header = screenState.category.displayName,
            onClick = { onItemClick(screenState.headlines?.get(it)) }
        )
        ScrollableTabRow(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(top = 20.dp, bottom = 4.dp),
            divider = {},
            edgePadding = 0.dp,
            selectedTabIndex = screenState.category.ordinal, containerColor = tabBackgroundColor,
            indicator = indicator
        ) {
            NewsCategory.values().forEachIndexed { index, category ->
                Tab(modifier = Modifier.zIndex(3f), text = { Text(text = category.displayName) },
                    selected = NewsCategory.values().indexOf(category) == index,
                    onClick = { viewModel.updateCategoryTab(category) }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F, fill = false)
                .pullRefresh(state = pullRefreshState)
                .verticalScroll(state = rememberScrollState())
        ) {

            Column(

            ) {

                HeaderWithTextButton(modifier = Modifier
                    .padding(vertical = 8.dp), onClick = {onShowAllHorizontalList(screenState.category)})

                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(screenState.headlines ?: listOf()) { article ->
                        ArticleVerticalCard(article = article, onClick = { onItemClick(article) })
                    }
                }

                HeaderWithTextButton(modifier = Modifier
                    .padding(vertical = 8.dp), onClick = {onShowAllVerticalList(screenState.category)})

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    screenState.headlines?.forEachIndexed { _, article ->
                        ArticleHorizontalCard(article = article, onClick = {
                            onItemClick(article)
                        })
                    }
                }


            }
            PullRefreshIndicator(
                refreshing = screenState.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                contentColor = highlightColor
//                backgroundColor = if (screenState.isLoading) Color.Red else Color.Green,
            )
        }
    }
}

@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, currentPage: Int) {
    val transition = updateTransition(currentPage, label = "")
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
            .fillMaxSize()
            .background(
                color = backgroundColor,
                CustomTabShape
            )
            .zIndex(1f)
    )

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .background(
                color = highlightColor,
                HighLightIndicatorShape(height = 6F)
            )
            .zIndex(2f)
    )
}