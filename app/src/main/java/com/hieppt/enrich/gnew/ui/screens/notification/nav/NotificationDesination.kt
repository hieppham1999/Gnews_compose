package com.hieppt.enrich.gnew.ui.screens.notification.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hieppt.enrich.gnew.data.model.Article
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.ui.screens.notification.NotificationScreen

object NotificationDestination : NavigationDestination {
    override val route = "notification_route"
    override val destination = "notification_destination"
}

fun NavGraphBuilder.notificationGraph(
    onBack: () -> Unit,
    onItemClick: (article: Article?) -> Unit,
) {
    composable(route = NotificationDestination.route + "/{category}", arguments = listOf(
        navArgument("category") {
            type = NavType.StringType
        }
    )) {
        NotificationScreen(onBack = onBack, onItemClick = onItemClick)
    }
}
