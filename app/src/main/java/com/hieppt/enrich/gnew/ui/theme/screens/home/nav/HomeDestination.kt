package com.hieppt.enrich.gnew.ui.theme.screens.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.theme.screens.home.HomeScreen

object HomeDestination : NavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    onBack: () -> Unit
) {
    composable(route = HomeDestination.route) {
        HomeScreen(onBack = onBack)
    }
}
