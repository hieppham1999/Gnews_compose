package com.hieppt.enrich.gnew.ui.theme.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    Text(text = "HomeTab", color = Color.Black)
}