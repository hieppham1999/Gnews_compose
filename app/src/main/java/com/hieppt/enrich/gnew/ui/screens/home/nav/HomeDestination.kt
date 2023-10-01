package com.hieppt.enrich.gnew.ui.screens.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.screens.home.HomeScreen

object HomeDestination : NavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    onBack: () -> Unit,
    onItemClick: (article: Article?) -> Unit,
    onShowAllVerticalList: (List<Article>) -> Unit,
    onShowAllHorizontalList: (List<Article>) -> Unit

) {
    composable(route = HomeDestination.route) {
        HomeScreen(
            onBack = onBack,
            onItemClick = { article ->
                if (article != null) {
                    onItemClick(article)
                }
            },
            onShowAllVerticalList = {
                if (it != null) {
                    println("here")
                    onShowAllVerticalList(it)
                }
            },
            onShowAllHorizontalList = {
                if (it != null) {
                    onShowAllHorizontalList(it)
                }
            }
        )
    }
}
