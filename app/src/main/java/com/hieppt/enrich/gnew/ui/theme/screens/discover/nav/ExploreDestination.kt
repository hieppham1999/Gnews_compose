package com.hieppt.enrich.gnew.ui.theme.screens.discover.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.theme.screens.home.HomeScreen

object ExploreDestination : NavigationDestination {
    override val route = "explore_route"
    override val destination = "explore_destination"
}

fun NavGraphBuilder.exploreGraph(
    onBack: () -> Unit
) {
    composable(route = ExploreDestination.route) {
        HomeScreen(onBack = onBack)
    }
}
