package com.hieppt.enrich.gnew.navigation

import androidx.annotation.DrawableRes

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val icon: Int
) : NavigationDestination
