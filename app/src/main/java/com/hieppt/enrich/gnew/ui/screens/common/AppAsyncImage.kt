package com.hieppt.enrich.gnew.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hieppt.enrich.gnew.R

@Composable
fun AppAsyncImage(modifier: Modifier = Modifier, url: String?, contentScale: ContentScale = ContentScale.Fit) {
    SubcomposeAsyncImage(modifier = modifier, model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .build(),
        contentDescription = null,
        contentScale = contentScale,
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
                contentScale = contentScale

            )
        })
}