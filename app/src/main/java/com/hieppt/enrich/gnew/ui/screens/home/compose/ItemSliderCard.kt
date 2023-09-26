package com.hieppt.enrich.gnew.ui.screens.home.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.ui.screens.common.CirclePagerIndicator


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorySliderCard(
    listItem: List<Article>?,
    header: String?,
    onClick: (index: Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)

    Box(
        modifier = Modifier
            .height(100.dp)
            .clickable(onClick = { onClick(pagerState.currentPage) })
            .clip(RoundedCornerShape(23.dp))
    ) {

        val pageCount = listItem?.size ?: 0

        HorizontalPager(
            state = pagerState, pageCount = pageCount
        ) { page ->
            SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(listItem?.get(page)?.image ?: "")
                .crossfade(true)
                .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .matchParentSize()
                            .align(Alignment.Center)
                    )
                },
                error = {
                    Image(
                        painter = painterResource(
                            id = R.drawable.img_article_placeholder
                        ), contentDescription = null,
                        contentScale = ContentScale.FillWidth

                    )
                })
        }
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
        Column(
            modifier = Modifier
                .padding(bottom = 7.dp)
                .fillMaxSize(),


            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom

        ) {
            header?.uppercase()?.let {
                Text(
                    text = it, modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            CirclePagerIndicator(
                modifier = Modifier
                    .width(
                        60.dp
                    )
                    .height(10.dp),
                count = pageCount,
                circleRadius = 3.0,
                selectedIndex = pagerState.currentPage
            )
        }
    }
}