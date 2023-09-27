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
    onItemClick: (article :Article?) -> Unit
) {
    composable(route = HomeDestination.route) {
        HomeScreen(onBack = onBack, onItemClick = onItemClick)
    }
}