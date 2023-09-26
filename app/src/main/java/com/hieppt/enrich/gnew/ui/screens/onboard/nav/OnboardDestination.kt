package com.hieppt.enrich.gnew.ui.screens.onboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.screens.onboard.OnboardScreen

object OnboardDestination : NavigationDestination {
    override val route = "onboard_route"
    override val destination = "onboard_destination"
}

fun NavGraphBuilder.onboardGraph(onButtonClicked: () -> Unit) {
    composable(route = OnboardDestination.route) {
        OnboardScreen(onButtonClicked = onButtonClicked)
    }
}