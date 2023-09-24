package com.hieppt.enrich.gnew.ui.theme.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.ui.theme.screens.common.CirclePagerIndicator

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val screenState by viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        UserGreeting(userName = "Hiep")
        CategorySliderCard(
            listItem = screenState.headlines?.subList(0, 4),
            header = screenState.category.displayName,
            onClick = {}
        )
    }
}

@Composable
fun UserGreeting(userName: String) {
    Row(
        modifier = Modifier
            .background(Color.DarkGray)
            .height(80.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hello $userName",
                style = MaterialTheme.typography.titleLarge.copy(lineHeight = 24.55.sp, fontWeight = FontWeight.W600)
            )
            Text(
                text = "Have a nice day",
                style = MaterialTheme.typography.labelMedium.copy(lineHeight = 16.37.sp, fontWeight = FontWeight.W400)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_home),
            modifier = Modifier
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
                .width(40.dp)
                .height(40.dp),

            contentDescription = null
        )
    }
}

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