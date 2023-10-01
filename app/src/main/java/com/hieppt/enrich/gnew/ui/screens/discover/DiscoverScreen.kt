package com.hieppt.enrich.gnew.ui.screens.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        viewModel.list.forEachIndexed { _, article ->
            ArticleVerticalCard(article = article, onClick = {
                onItemClick(article)
            })
        }
    }
}