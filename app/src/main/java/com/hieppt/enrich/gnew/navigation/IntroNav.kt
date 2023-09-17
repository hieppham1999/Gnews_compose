package com.hieppt.enrich.gnew.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.hieppt.enrich.gnew.ui.theme.screens.onboard.IntroState
import com.hieppt.enrich.gnew.ui.theme.screens.onboard.nav.OnboardDestination
import com.hieppt.enrich.gnew.ui.theme.screens.onboard.nav.onboardGraph
import com.hieppt.enrich.gnew.ui.theme.screens.onboard.rememberIntroState

@Composable
fun IntroNav(
    introState: IntroState = rememberIntroState()
) {
    val context = LocalContext.current

    NavHost(
        navController = introState.navController,
        startDestination = OnboardDestination.route
    ) {
        onboardGraph(
            onBack = {}
        )
    }
}