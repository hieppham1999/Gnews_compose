package com.hieppt.enrich.gnew.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.hieppt.enrich.gnew.ui.theme.AppState
import com.hieppt.enrich.gnew.ui.theme.rememberAppState
import com.hieppt.enrich.gnew.ui.theme.screens.discover.nav.exploreGraph
import com.hieppt.enrich.gnew.ui.theme.screens.home.nav.HomeDestination
import com.hieppt.enrich.gnew.ui.theme.screens.home.nav.homeGraph
import com.hieppt.enrich.gnew.ui.theme.screens.my_profile.nav.myProfileGraph
import com.hieppt.enrich.gnew.ui.theme.screens.notification.nav.notificationGraph

@Composable
fun ScreenNav(
    appState: AppState = rememberAppState()
) {
    val context = LocalContext.current
    NavHost(
        navController = appState.navController,
        startDestination = HomeDestination.route
    ) {
        homeGraph {
            appState.back()
        }
        exploreGraph {
            appState.back()
        }
        notificationGraph { appState.back() }
        myProfileGraph { appState.back() }
    }
}