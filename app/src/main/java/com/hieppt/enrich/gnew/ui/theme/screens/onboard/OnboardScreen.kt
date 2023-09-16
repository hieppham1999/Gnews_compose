package com.hieppt.enrich.gnew.ui.theme.screens.onboard

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.ui.theme.poppinsFontFamily
import com.hieppt.enrich.gnew.ui.theme.screenWidth
import com.hieppt.enrich.gnew.ui.theme.screens.common.CirclePagerIndicator
import com.hieppt.enrich.gnew.ui.theme.screens.onboard.composes.GettingStartedButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen(
    viewModel: OnboardViewModel = hiltViewModel()
) {

    val pagerState by viewModel.currentPageIndex.collectAsState()
    Box {
        HorizontalPager(
            pageCount = viewModel.introData.size,
            state = pagerState,
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
        Row(
            modifier = Modifier
                .height(100.dp)
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

@Preview
@Composable
fun OnboardScreenPreview() {
    OnboardScreen()
}