package com.hieppt.enrich.gnew.navigation
import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.hieppt.enrich.gnew.MainActivity
import com.hieppt.enrich.gnew.ui.screens.onboard.IntroState
import com.hieppt.enrich.gnew.ui.screens.onboard.nav.OnboardDestination
import com.hieppt.enrich.gnew.ui.screens.onboard.nav.onboardGraph
import com.hieppt.enrich.gnew.ui.screens.onboard.rememberIntroState

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
            onButtonClicked = {
                context.apply {
                    startActivity(Intent(this, MainActivity::class.java))
                    (this as? Activity)?.finish()
                }
            }
        )
    }
}