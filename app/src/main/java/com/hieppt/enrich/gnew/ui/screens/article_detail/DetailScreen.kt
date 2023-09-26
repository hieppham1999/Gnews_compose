package com.hieppt.enrich.gnew.ui.screens.article_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.ui.theme.dividerColor
import com.hieppt.enrich.gnew.ui.theme.textColor2
import com.hieppt.enrich.gnew.ui.theme.textDefaultColor

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    viewModel: ArticleDetailViewModel = hiltViewModel(),
) {

    val screenState by viewModel.screenState.collectAsState()

    Box() {
        SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(screenState.article?.image)
            .crossfade(true)
            .build(),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
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
                    contentScale = ContentScale.Inside

                )
            })
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
                            0.3F to Color(0xFF30312D).copy(alpha = 0.95F),
                            1F to Color(0xFF30312D).copy(alpha = 1F)
                        )
                    )
            )
            Column {

                val subtitleTextStyle = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 9.61.sp,
                    lineHeight = 31.47.sp,
                    fontWeight = FontWeight.W300
                )

                Text(
                    text = screenState.article?.title ?: "", maxLines = 2,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 23.07.sp,
                        lineHeight = 31.47.sp,
                        fontWeight = FontWeight.W700
                    )
                )
                Text(
                    buildAnnotatedString {
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
                                fontSize = subtitleTextStyle.fontSize,
                                fontWeight = subtitleTextStyle.fontWeight,
                                color = textColor2
                            )
                        ) {
                            append(screenState.article?.source?.name ?: "")
                        }
                    }
                )
                Divider(
                    color = dividerColor, modifier = Modifier
                        .fillMaxWidth()
                        .height(0.38.dp)
                )
                Text(
                    text = "Published ${screenState.article?.publishedAt?.subSequence(0,10)}",
                    style = subtitleTextStyle
                )
            }
        }

    }


}