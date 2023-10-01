package com.hieppt.enrich.gnew.navigation

import androidx.annotation.DrawableRes
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.ui.screens.discover.nav.ExploreDestination
import com.hieppt.enrich.gnew.ui.screens.home.nav.HomeDestination
import com.hieppt.enrich.gnew.ui.screens.my_profile.nav.MyProfileDestination
import com.hieppt.enrich.gnew.ui.screens.notification.nav.NotificationDestination

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val icon: Int
) : NavigationDestination

object TopLevelDestinationItem {
    val HOME = TopLevelDestination(
        HomeDestination.route,
        HomeDestination.destination,
        icon = R.drawable.ic_home,
    )
    val EXPLORE = TopLevelDestination(
        ExploreDestination.route,
        ExploreDestination.destination,
        icon = R.drawable.ic_explore,
    )
    val NOTIFY = TopLevelDestination(
        NotificationDestination.route,
        NotificationDestination.destination,
        icon = R.drawable.ic_notification,
    )
    val PROFILE = TopLevelDestination(
        MyProfileDestination.route,
        MyProfileDestination.destination,
        icon = R.drawable.ic_profile,
    )

}