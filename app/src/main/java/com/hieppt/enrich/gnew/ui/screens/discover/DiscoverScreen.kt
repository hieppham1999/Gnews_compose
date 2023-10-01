package com.hieppt.enrich.gnew.ui.screens.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.data.model.Article
import com.hieppt.enrich.gnew.ui.screens.common.ArticleVerticalCard
import com.hieppt.enrich.gnew.ui.theme.appDefaultPadding
import com.hieppt.enrich.gnew.ui.theme.defaultItemSpacing

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onItemClick: (article: Article?) -> Unit
) {

    val screenState = viewModel.screenState.collectAsState()

    if (screenState.value.articleList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center), text = "No Data"
            )
        }
    } else {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(appDefaultPadding),
            columns = GridCells.Adaptive(minSize = 128.dp),
            verticalArrangement = Arrangement.spacedBy(defaultItemSpacing),
            horizontalArrangement = Arrangement.spacedBy(defaultItemSpacing)
        ) {
            items(count = screenState.value.articleList.size) { index ->
                ArticleVerticalCard(article = screenState.value.articleList[index], onClick = {
                    onItemClick(screenState.value.articleList[index])
                })
            }
        }
    }

}