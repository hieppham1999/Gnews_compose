package com.hieppt.enrich.gnew.ui.theme.screens.onboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.theme.screens.home.HomeScreen

object OnboardDestination : NavigationDestination {
    override val route = "onboard_route"
    override val destination = "onboard_destination"
}

fun NavGraphBuilder.onboardGraph(onBack: () -> Unit) {
    composable(route = OnboardDestination.route) {
        HomeScreen(onBack = onBack)
    }
}