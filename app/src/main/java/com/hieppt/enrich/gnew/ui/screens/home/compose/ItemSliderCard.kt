package com.hieppt.enrich.gnew.ui.screens.home.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.helper.getTextHeight
import com.hieppt.enrich.gnew.helper.textWidth
import com.hieppt.enrich.gnew.ui.screens.common.AppAsyncImage
import com.hieppt.enrich.gnew.ui.screens.common.CirclePagerIndicator
import com.hieppt.enrich.gnew.ui.theme.boxColor


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorySliderCard(
    modifier: Modifier = Modifier,
    listItem: List<Article>?,
    header: String?,
    onClick: (index: Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)

    Box(
        modifier = modifier
            .clickable(onClick = { onClick(pagerState.currentPage) })
            .clip(RoundedCornerShape(23.dp))
    ) {

        val pageCount = listItem?.size ?: 0

        val startPadding = 16.dp

        val titleLineHeight = 21.0

        HorizontalPager(
            state = pagerState, pageCount = pageCount
        ) { page ->

            AppAsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                url = listItem?.get(page)?.image,
                contentScale = ContentScale.FillWidth
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            0.4F to Color.Transparent,
                            0.8F to Color(0xFF24251E).copy(alpha = 0.77F),
                            1F to Color(0xFF25261F)
                        )
                    )
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 7.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom

        ) {
            header?.uppercase()?.let {
                Box(
                    modifier = Modifier
                        .padding(bottom = 16.dp, start = startPadding)
                        .fillMaxWidth()
                ) {

                    val firstCharWidth = textWidth(it[0].toString(), fontSize = 14F, context = LocalContext.current)
                    val firstCharHeight = getTextHeight(it[0].toString(), fontSize = 14F, context = LocalContext.current)

                    Box(
                        modifier
                            .size(firstCharWidth.dp, height = 1.dp)
                            .background(color = boxColor)

                    )
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(start = (firstCharWidth / 2).dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            lineHeight = titleLineHeight.sp
                        )
                    )
                }
            }
            CirclePagerIndicator(
                modifier = Modifier
                    .width(
                        60.dp
                    )
                    .height(10.dp),
                count = pageCount,
                circleRadius = 4.0,
                selectedIndex = pagerState.currentPage
            )
        }
    }
}