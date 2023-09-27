package com.hieppt.enrich.gnew.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.data.Article
import com.hieppt.enrich.gnew.ui.theme.cardBackgroundColor

@Composable
fun ArticleVerticalCard(
    article: Article?,
    onClick: () -> Unit,
    width: Double = 130.0,
) {

    val borderRadius = 16.3.dp

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(borderRadius))
            .clickable(onClick = onClick)
            .width(width.dp)
            .background(cardBackgroundColor)
            .padding(8.dp)

            .shadow(
                elevation = 76.91119384765625.dp,
                spotColor = Color(0x1281D8F1),
                ambientColor = Color(0x1281D8F1)
            ),
        colors = cardColors(containerColor = cardBackgroundColor)

    ) {
        AppAsyncImage(
            modifier = Modifier
                .aspectRatio(1.0F)
                .clip(RoundedCornerShape(borderRadius.value.times(0.75).dp)),
            url = article?.image,
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = article?.title ?: "Text here",
                maxLines = 3,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 8.65.sp,
                    lineHeight = 11.8.sp,
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(end = 4.dp),
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = null,
                )
                Text(
                    text = "Read".uppercase(),
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W600,
                        fontSize = 5.57.sp,
                        letterSpacing = 0.29.sp,
                    )
                )
            }
        }

    }
}