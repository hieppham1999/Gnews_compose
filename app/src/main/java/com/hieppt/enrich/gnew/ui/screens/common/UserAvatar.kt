package com.hieppt.enrich.gnew.ui.screens.common

import android.graphics.Bitmap
import android.media.audiofx.DynamicsProcessing.Mbc
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.hieppt.enrich.gnew.R

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    bitmapImage: Bitmap?,
    contentScale: ContentScale = ContentScale.Fit
) {
    if (bitmapImage != null) Image(
        modifier = modifier,
        bitmap = bitmapImage.asImageBitmap(),
        contentScale = contentScale,
        contentDescription = null,
    ) else Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.img_default_avatar),
        contentDescription = null,
        contentScale = contentScale
    )
}