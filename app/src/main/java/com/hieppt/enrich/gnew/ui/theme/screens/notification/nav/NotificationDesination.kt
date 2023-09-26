package com.hieppt.enrich.gnew.ui.theme.screens.notification.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.theme.screens.home.HomeScreen

object NotificationDestination : NavigationDestination {
    override val route = "notification_route"
    override val destination = "notification_destination"
}

fun NavGraphBuilder.notificationGraph(
    onBack: () -> Unit,
) {
    composable(route = NotificationDestination.route) {

    }
}
