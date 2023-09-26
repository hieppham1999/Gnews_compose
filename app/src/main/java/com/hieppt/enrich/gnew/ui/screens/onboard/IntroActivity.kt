package com.hieppt.enrich.gnew.ui.screens.onboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hieppt.enrich.gnew.navigation.IntroNav
import com.hieppt.enrich.gnew.ui.theme.GnewsComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GnewsComposeTheme {
                IntroNav()
            }
        }
    }
}