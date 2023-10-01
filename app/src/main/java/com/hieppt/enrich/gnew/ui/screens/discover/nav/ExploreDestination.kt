package com.hieppt.enrich.gnew.ui.screens.discover.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination
import com.hieppt.enrich.gnew.ui.screens.discover.DiscoverScreen

object ExploreDestination : NavigationDestination {
    override val route = "explore_route"
    override val destination = "explore_destination"
}

fun NavGraphBuilder.exploreGraph(
    onBack: () -> Unit,
    onItemClick: (article: Article?) -> Unit,
) {
    composable(route = ExploreDestination.route + "/list/{dataString}", arguments = listOf(
        navArgument("dataString") {
            type = NavType.StringType
        }
    )) {
        DiscoverScreen(onBack = onBack, onItemClick = onItemClick,)
    }
}
