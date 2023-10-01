package com.hieppt.enrich.gnew.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

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

val appDefaultPadding = 16.dp
val defaultItemSpacing = 15.dp
