package com.hieppt.enrich.gnew.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.ui.theme.screenWidth

@Composable
fun CirclePagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    selectedIndex: Int,
    circleRadius: Double? = null
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(
            count = count,
        ) { index ->
            val baseCircleWidth = circleRadius?.times(2) ?: (screenWidth() * 0.022)

            if (index == selectedIndex) {
                Image(
                    painter = painterResource(id = R.drawable.forward_button_circle),
                    modifier = Modifier.width(1.4 * baseCircleWidth.dp),
                    contentDescription = null,
                )
            } else {
                Box(
                    modifier = Modifier
                        .width(baseCircleWidth.dp)
                        .aspectRatio(1f)
                        .background(Color(0xFF4F4F4F), shape = CircleShape)
                        .clip(CircleShape)
                )
            }
        }
    }
}