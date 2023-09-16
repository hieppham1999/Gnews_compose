package com.hieppt.enrich.gnew.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
@Composable
fun screenHeight(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp
}
@Composable
fun screenWidth(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp
}