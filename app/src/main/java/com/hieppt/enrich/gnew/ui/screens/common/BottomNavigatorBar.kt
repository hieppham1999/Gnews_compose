package com.hieppt.enrich.gnew.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.hieppt.enrich.gnew.helper.checkRouteStartWith
import com.hieppt.enrich.gnew.navigation.NavigationDestination
import com.hieppt.enrich.gnew.navigation.TopLevelDestination
import com.hieppt.enrich.gnew.ui.theme.backgroundColor
import com.hieppt.enrich.gnew.ui.theme.highlightColor
import com.hieppt.enrich.gnew.ui.theme.tabUnselectedColor

@Composable
fun BottomNavigationBar(
    items: List<TopLevelDestination>,
    onNavigateToDestination: (NavigationDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar(
        containerColor = backgroundColor
    ) {
        items.forEach { destination ->



            val selected =
                currentDestination?.hierarchy?.any {


                    println("Check isSelected")
                    println(it.route)
                    println(destination.route)

                    checkRouteStartWith(
                        text = it.route,
                        startWith = destination.route
                    )
                } == true
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = highlightColor,
                    unselectedIconColor = tabUnselectedColor
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = destination.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .height(if (selected) 25.dp else 15.dp),
                        )
                        if (selected) Box(
                            modifier = Modifier
                                .size(width = 4.dp, height = 4.dp)
                                .padding(top = 4.dp)
                                .background(color = highlightColor, shape = CircleShape)
                        )


                    }
                }
            )
        }
    }
}