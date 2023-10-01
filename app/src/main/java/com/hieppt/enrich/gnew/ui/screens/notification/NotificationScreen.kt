package com.hieppt.enrich.gnew.ui.screens.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.data.model.Article
import com.hieppt.enrich.gnew.ui.screens.common.ArticleHorizontalCard
import com.hieppt.enrich.gnew.ui.screens.discover.DiscoverViewModel
import com.hieppt.enrich.gnew.ui.theme.appDefaultPadding
import com.hieppt.enrich.gnew.ui.theme.defaultItemSpacing

@Composable
fun NotificationScreen(
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(appDefaultPadding),
            verticalArrangement = Arrangement.spacedBy(defaultItemSpacing)
        ) {
            items(screenState.value.articleList.size) { index ->
                val item = screenState.value.articleList[index]
                ArticleHorizontalCard(article = item, onClick = {
                    onItemClick(item)
                })
            }
        }
    }


}