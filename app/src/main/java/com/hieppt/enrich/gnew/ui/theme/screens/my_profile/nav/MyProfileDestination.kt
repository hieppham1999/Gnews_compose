package com.hieppt.enrich.gnew.ui.theme.screens.my_profile.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.theme.screens.home.HomeScreen

object MyProfileDestination : NavigationDestination {
    override val route = "my_profile_route"
    override val destination = "my_profile_destination"
}

fun NavGraphBuilder.myProfileGraph(
    onBack: () -> Unit
) {
    composable(route = MyProfileDestination.route) {
        HomeScreen(onBack = onBack)
    }
}