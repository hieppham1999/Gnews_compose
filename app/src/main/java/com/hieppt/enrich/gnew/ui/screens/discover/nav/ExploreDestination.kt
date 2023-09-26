package com.hieppt.enrich.gnew.ui.screens.discover.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.navigation.NavigationDestination

object ExploreDestination : NavigationDestination {
    override val route = "explore_route"
    override val destination = "explore_destination"
}

fun NavGraphBuilder.exploreGraph(
    onBack: () -> Unit
) {
    composable(route = ExploreDestination.route) {
    }
}
