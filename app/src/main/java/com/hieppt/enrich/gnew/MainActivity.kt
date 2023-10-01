package com.hieppt.enrich.gnew

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hieppt.enrich.gnew.data.model.NewsCategory
import com.hieppt.enrich.gnew.helper.checkRouteStartWith
import com.hieppt.enrich.gnew.navigation.ScreenNav
import com.hieppt.enrich.gnew.navigation.TopLevelDestinationItem
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination
import com.hieppt.enrich.gnew.ui.screens.common.BottomNavigationBar
import com.hieppt.enrich.gnew.ui.screens.discover.nav.ExploreDestination
import com.hieppt.enrich.gnew.ui.screens.notification.nav.NotificationDestination
import com.hieppt.enrich.gnew.ui.theme.GnewsComposeTheme
import com.hieppt.enrich.gnew.ui.theme.backgroundColor
import com.hieppt.enrich.gnew.ui.theme.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GnewsComposeTheme {
                val appState = rememberAppState()

                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()

                bottomBarState.value = checkRouteStartWith(
                    text = navBackStackEntry?.destination?.route,
                    startWith = ArticleDetailDestination.route
                ) != true

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = bottomBarState.value,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            BottomNavigationBar(
                                items = appState.bottomBarItems,
                                onNavigateToDestination = {
                                    when (it.route) {
                                        ExploreDestination.route -> appState.navigate(
                                            TopLevelDestinationItem.EXPLORE,
                                            route = ExploreDestination.route + "/${NewsCategory.General.name}"
                                        )
                                        NotificationDestination.route -> appState.navigate(
                                            TopLevelDestinationItem.NOTIFY,
                                            route = NotificationDestination.route + "/${NewsCategory.General.name}"
                                        )
                                        else -> appState.navigate(it)

                                    }
                                },
                                currentDestination = appState.currentDestination
                            )
                        }
                    }
                ) {
                    Surface(color = backgroundColor, modifier = Modifier.padding(it)) {
                        ScreenNav(appState)
                    }
                }
            }
        }
    }
}
