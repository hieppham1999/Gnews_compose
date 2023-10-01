package com.hieppt.enrich.gnew

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hieppt.enrich.gnew.helper.articleDetailRouteRegex
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.navigation.ScreenNav
import com.hieppt.enrich.gnew.navigation.TopLevelDestination
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination
import com.hieppt.enrich.gnew.ui.screens.common.BottomNavigationBar
import com.hieppt.enrich.gnew.ui.theme.GnewsComposeTheme
import com.hieppt.enrich.gnew.ui.theme.backgroundColor
import com.hieppt.enrich.gnew.ui.theme.rememberAppState
import com.hieppt.enrich.gnew.ui.theme.highlightColor
import com.hieppt.enrich.gnew.ui.theme.tabUnselectedColor
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

                println(navBackStackEntry?.destination?.route)

                bottomBarState.value = navBackStackEntry?.destination?.route?.matches(
                    Regex(articleDetailRouteRegex)) != true

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = bottomBarState.value,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            BottomNavigationBar(
                                items = appState.bottomBarItems,
                                onNavigateToDestination = appState::navigate,
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
