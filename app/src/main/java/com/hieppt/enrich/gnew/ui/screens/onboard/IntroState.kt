package com.hieppt.enrich.gnew.ui.screens.onboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.hieppt.enrich.gnew.navigation.NavigationDestination

@Composable
fun rememberIntroState(
    navController: NavHostController = rememberNavController()
): IntroState {
    return remember(navController) {
        IntroState(navController)
    }
}

class IntroState(
    val navController: NavHostController
) {
    fun navigate(
        destination: NavigationDestination,
        route: String? = null,
        builder: (NavOptionsBuilder.() -> Unit)? = null) {
        if (builder != null) {
            navController.navigate(route ?: destination.route, builder)
        } else {
            navController.navigate(route ?: destination.route)
        }
    }

    fun back(destination: NavigationDestination,
             route: String? = null,
             inclusive: Boolean = false
    ) {
        navController.popBackStack(route ?: destination.route, inclusive)
    }
}