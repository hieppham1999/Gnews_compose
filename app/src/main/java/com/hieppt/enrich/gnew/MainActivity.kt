package com.hieppt.enrich.gnew

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.navigation.ScreenNav
import com.hieppt.enrich.gnew.navigation.TopLevelDestination
import com.hieppt.enrich.gnew.ui.theme.GnewsComposeTheme
import com.hieppt.enrich.gnew.ui.theme.rememberAppState
import com.hieppt.enrich.gnew.ui.theme.tabSelectedColor
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
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = appState.bottomBarItems,
                            onNavigateToDestination = appState::navigate,
                            currentDestination = appState.currentDestination
                        )
                    }
                ) {
                    Surface(modifier = Modifier.padding(it)) {
                        ScreenNav(appState)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<TopLevelDestination>,
    onNavigateToDestination: (NavigationDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEach { destination ->
            val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = tabSelectedColor,
                    unselectedIconColor = tabUnselectedColor
                ),
                icon = {

                }
            )
        }
    }
}
