package com.hieppt.enrich.gnew.ui.screens.article_detail.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.screens.article_detail.DetailScreen

object ArticleDetailDestination : NavigationDestination {
    override val route = "details"
    override val destination = "home_destination"
}

fun NavGraphBuilder.detailGraph(
    onBack: () -> Unit
) {
    composable(route = ArticleDetailDestination.route + "/data/{dataString}", arguments = listOf(
        navArgument("dataString") {
            type = NavType.StringType
        }
    )) {
        DetailScreen(onBack = onBack)

    }
}