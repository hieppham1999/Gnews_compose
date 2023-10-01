package com.hieppt.enrich.gnew.ui.screens.discover
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.ui.screens.common.ArticleVerticalCard

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onItemClick: (article: Article?) -> Unit
) {

    val screenState = viewModel.screenState.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(count = screenState.value.articleList.size) { index ->
            ArticleVerticalCard(article = screenState.value.articleList[index], onClick = {
                onItemClick(screenState.value.articleList[index])
            })
        }
    }
}