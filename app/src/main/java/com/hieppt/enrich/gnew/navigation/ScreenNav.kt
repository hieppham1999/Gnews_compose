package com.hieppt.enrich.gnew.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.detailGraph
import com.hieppt.enrich.gnew.ui.screens.discover.nav.exploreGraph
import com.hieppt.enrich.gnew.ui.screens.home.nav.HomeDestination
import com.hieppt.enrich.gnew.ui.screens.home.nav.homeGraph
import com.hieppt.enrich.gnew.ui.screens.my_profile.nav.myProfileGraph
import com.hieppt.enrich.gnew.ui.screens.notification.nav.notificationGraph
import com.hieppt.enrich.gnew.ui.theme.AppState
import com.hieppt.enrich.gnew.ui.theme.rememberAppState


@Composable
fun ScreenNav(
    appState: AppState = rememberAppState()
) {
    val context = LocalContext.current
    NavHost(
        navController = appState.navController,
        startDestination = HomeDestination.route
    ) {
        homeGraph(
            { appState.back() },
            { it -> appState.navController.navigate(ArticleDetailDestination.route + "/${it?.url}") },
        )
        exploreGraph {
            appState.back()
        }
        notificationGraph { appState.back() }
        myProfileGraph { appState.back() }
        detailGraph { appState.back() }

    }
}