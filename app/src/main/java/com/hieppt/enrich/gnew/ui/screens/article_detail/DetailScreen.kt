package com.hieppt.enrich.gnew.ui.screens.article_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.ui.screens.common.AppAsyncImage
import com.hieppt.enrich.gnew.ui.theme.backgroundColor
import com.hieppt.enrich.gnew.ui.theme.dividerColor
import com.hieppt.enrich.gnew.ui.theme.fabColor
import com.hieppt.enrich.gnew.ui.theme.highlightColor
import com.hieppt.enrich.gnew.ui.theme.tabUnselectedColor2
import com.hieppt.enrich.gnew.ui.theme.textColor2
import com.hieppt.enrich.gnew.ui.theme.textColor3
import com.hieppt.enrich.gnew.ui.theme.textDefaultColor

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    viewModel: ArticleDetailViewModel = hiltViewModel(),
) {

    val screenState by viewModel.screenState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AppAsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            url = screenState.article?.image,
            contentScale = ContentScale.FillHeight
        )

        FloatingActionButton(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(top = 16.dp, start = 16.dp)
            .width(24.dp)
            .height(24.dp),
            shape = CircleShape,
            containerColor = fabColor,
            content = {
                Image(
                    painterResource(id = R.drawable.ic_backward_arrow),
                    modifier = Modifier
                        .matchParentSize(),
                    contentDescription = null
                )
            },
            onClick = { onBack() })
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxHeight(0.55F)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(
                        Brush.verticalGradient(
                            0F to Color.Transparent,
                            0.3F to Color(0xFF30312D).copy(alpha = 0.65F),
                            1F to Color(0xFF30312D).copy(alpha = 1F)
                        )
                    )
            )
            ArticleContent(article = screenState.article)


        }

    }


}

@Composable
fun ArticleContent(modifier: Modifier = Modifier, article: Article?) {

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Overview", "Album", "Discussion")

    Column {
        Column(
            modifier = Modifier.padding(start = 19.dp, end = 19.dp)
        ) {

            val subtitleTextStyle = MaterialTheme.typography.bodySmall.copy(
                fontSize = 9.61.sp, lineHeight = 31.47.sp, fontWeight = FontWeight.W300
            )

            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = article?.title ?: "",
                maxLines = 2,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 23.07.sp, lineHeight = 31.47.sp, fontWeight = FontWeight.W700
                )
            )
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = subtitleTextStyle.fontSize,
                        fontWeight = subtitleTextStyle.fontWeight,
                        color = textDefaultColor
                    )
                ) {
                    append("By ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = subtitleTextStyle.fontSize, fontWeight = FontWeight.W600, color = textColor2
                    )
                ) {
                    append(article?.source?.name ?: "")
                }
            })
            Divider(
                color = dividerColor, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(0.4.dp)
            )
            Text(
                text = "Published ${article?.publishedAt?.subSequence(0, 10)}", style = subtitleTextStyle
            )

            ScrollableTabRow(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp)),
                divider = { },
                edgePadding = 0.dp,
                selectedTabIndex = tabIndex, containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        height = 1.0.dp,
                        color = highlightColor,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex])
//                            .width(tabPositions[tabIndex].width.value.times(0.5).dp)
                    )
                },
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(modifier = Modifier.zIndex(6f), text = {
                        Text(
                            text = tab, color = if (tabIndex == index) highlightColor else tabUnselectedColor2
                        )
                    }, selected = tabIndex == index, onClick = { tabIndex = index })
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(color = backgroundColor)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            when (tabIndex) {
                0 -> Column {
                    Text(
                        text = article?.content ?: "", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W400, fontSize = 11.54.sp, lineHeight = 17.31.sp, color = textColor3
                        )
                    )
                    AppAsyncImage(
                        modifier = Modifier.clip(RoundedCornerShape(14.dp)),
                        url = article?.image,
                        contentScale = ContentScale.FillWidth
                    )

                }

                1 -> AppAsyncImage(
                    modifier = Modifier.clip(RoundedCornerShape(14.dp)), url = article?.image
                )

                2 -> Column {
                    Text(
                        text = article?.source?.url ?: "", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W400, fontSize = 11.54.sp, lineHeight = 17.31.sp, color = textColor3
                        )
                    )

                }
            }
        }

    }
}

