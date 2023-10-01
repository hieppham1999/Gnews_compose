package com.hieppt.enrich.gnew.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.google.gson.Gson
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.detailGraph
import com.hieppt.enrich.gnew.ui.screens.discover.nav.ExploreDestination
import com.hieppt.enrich.gnew.ui.screens.discover.nav.exploreGraph
import com.hieppt.enrich.gnew.ui.screens.home.nav.HomeDestination
import com.hieppt.enrich.gnew.ui.screens.home.nav.homeGraph
import com.hieppt.enrich.gnew.ui.screens.my_profile.nav.myProfileGraph
import com.hieppt.enrich.gnew.ui.screens.notification.nav.NotificationDestination
import com.hieppt.enrich.gnew.ui.screens.notification.nav.notificationGraph
import com.hieppt.enrich.gnew.ui.theme.AppState
import com.hieppt.enrich.gnew.ui.theme.rememberAppState
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun ScreenNav(
    appState: AppState = rememberAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = HomeDestination.route
    ) {


        homeGraph(
            { appState.back() },
            { it ->
                val encode = URLEncoder.encode(Gson().toJson(it), StandardCharsets.UTF_8.toString())
                appState.navController.navigate(ArticleDetailDestination.route + "/data/${encode}")
            },
            onShowAllVerticalList = { it ->
                appState.navigate(TopLevelDestinationItem.EXPLORE, route = ExploreDestination.route + "/${it.name}")
            },
            onShowAllHorizontalList = { it ->
                appState.navigate(TopLevelDestinationItem.NOTIFY, route = NotificationDestination.route + "/${it.name}")
            }
        )
        exploreGraph(
            onBack = { appState.back() },
            onItemClick = { it ->
                val encode = URLEncoder.encode(Gson().toJson(it), StandardCharsets.UTF_8.toString())
                appState.navigate(ArticleDetailDestination, route = ArticleDetailDestination.route + "/data/${encode}")
            },
        )
        notificationGraph(
            onBack = { appState.back() },
            onItemClick = { it ->
                val encode = URLEncoder.encode(Gson().toJson(it), StandardCharsets.UTF_8.toString())
                appState.navigate(ArticleDetailDestination, route = ArticleDetailDestination.route + "/data/${encode}")
            },
        )
        myProfileGraph { appState.back() }
        detailGraph { appState.back() }

    }
}