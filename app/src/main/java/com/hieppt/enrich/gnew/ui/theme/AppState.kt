package com.hieppt.enrich.gnew.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.navigation.TopLevelDestination
import com.hieppt.enrich.gnew.navigation.TopLevelDestinationItem
import com.hieppt.enrich.gnew.ui.screens.discover.nav.ExploreDestination
import com.hieppt.enrich.gnew.ui.screens.home.nav.HomeDestination
import com.hieppt.enrich.gnew.ui.screens.my_profile.nav.MyProfileDestination
import com.hieppt.enrich.gnew.ui.screens.notification.nav.NotificationDestination

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(navController) {
        AppState(navController)
    }
}

class AppState(
    val navController: NavHostController
) {
    val bottomBarItems = arrayListOf(
        TopLevelDestinationItem.HOME,
        TopLevelDestinationItem.EXPLORE,
        TopLevelDestinationItem.NOTIFY,
        TopLevelDestinationItem.PROFILE
    )

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigate(
        destination: NavigationDestination,
        route: String? = null) {
        if (destination is TopLevelDestination) {

            println("IS TOP DEST")

            navController.navigate(route ?: destination.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        } else {
            navController.navigate(route ?: destination.route)
        }
    }

    fun back() {
        navController.popBackStack()
    }

    fun back(destination: NavigationDestination,
             route: String? = null,
             inclusive: Boolean = false
    ) {
        navController.popBackStack(route ?: destination.route, inclusive)
    }
}