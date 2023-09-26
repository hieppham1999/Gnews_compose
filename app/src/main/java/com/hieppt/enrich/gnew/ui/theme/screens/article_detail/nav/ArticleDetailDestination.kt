package com.hieppt.enrich.gnew.ui.theme.screens.article_detail.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.theme.screens.article_detail.DetailScreen
import com.hieppt.enrich.gnew.ui.theme.screens.home.HomeScreen

object ArticleDetailDestination : NavigationDestination {
    override val route = "detail_destination"
    override val destination = "home_destination"
}

fun NavGraphBuilder.detailGraph(
    onBack: () -> Unit
) {
    composable(route = ArticleDetailDestination.route + "/{url}") {
        DetailScreen(onBack = onBack)
    }
}