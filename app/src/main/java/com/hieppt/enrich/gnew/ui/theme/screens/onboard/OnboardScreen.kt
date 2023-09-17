package com.hieppt.enrich.gnew.ui.theme.screens.onboard

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.ui.theme.poppinsFontFamily
import com.hieppt.enrich.gnew.ui.theme.screens.common.CirclePagerIndicator
import com.hieppt.enrich.gnew.ui.theme.screens.onboard.composes.GettingStartedButton
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen(
    viewModel: OnboardViewModel = hiltViewModel()
) {

    val pagerState by viewModel.currentPageIndex.collectAsState()
    Box {
        SlideShowBackground(viewModel, pagerState)
        Row(
            modifier = Modifier
                .height(80.dp)
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                CirclePagerIndicator(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .width(
                            60.dp
                        )
                        .fillMaxHeight(),
                    count = viewModel.introData.size,
                    selectedIndex = pagerState.currentPage
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                GettingStartedButton()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun SlideShowBackground(
    viewModel: OnboardViewModel,
    pagerState: PagerState
) {

    var targetIndex by remember {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()

    suspend fun playBackground() {
        delay(3500)
        if (viewModel.isAutoScroll) {
            targetIndex = (pagerState.currentPage + 1) % viewModel.introData.size
            if (targetIndex != 0) {
                pagerState.animateScrollToPage(targetIndex, animationSpec = tween(1500))
            } else {
                pagerState.animateScrollToPage(targetIndex, animationSpec = tween(500))
            }
        }
    }

    LaunchedEffect(pagerState.isScrollInProgress) {
        val job = scope.launch {
            if (!pagerState.isScrollInProgress && viewModel.isAutoScroll) {
                playBackground()
            } else {
                return@launch
            }
        }

        if (pagerState.isScrollInProgress) {
            println(4)
            job.cancelChildren()
        }
    }

    HorizontalPager(
        pageCount = viewModel.introData.size,
        state = pagerState,
        userScrollEnabled = false
    )
    {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = viewModel.introData[it].imageUrl.toIntOrNull() ?: R.drawable.discover
                ),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(
                        Brush.verticalGradient(
                            0F to Color.Transparent,
                            0.3F to Color.Black.copy(alpha = 0.95F),
                            1F to Color.Black.copy(alpha = 1F)
                        )
                    )
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = viewModel.introData[it].title,
                    fontSize = 30.sp,
                    lineHeight = 46.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = poppinsFontFamily,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 2
                )
                Text(
                    text = viewModel.introData[it].subtitle,
                    fontSize = 14.sp,
                    lineHeight = 17.84.sp,
                    fontWeight = FontWeight.W300,
                    fontFamily = poppinsFontFamily,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 2
                )
            }


        }
    }
}