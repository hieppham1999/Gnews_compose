package com.hieppt.enrich.gnew.ui.screens.home.compose

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.ui.screens.common.UserAvatar

@Composable
fun UserGreeting(userName: String? = "User", imageBitmap: Bitmap?) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .height(80.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hello $userName",
                style = MaterialTheme.typography.titleLarge.copy(
                    lineHeight = 24.55.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Text(
                text = "Have a nice day",
                style = MaterialTheme.typography.labelMedium.copy(
                    lineHeight = 16.37.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
        UserAvatar(
            modifier = Modifier
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
                .width(40.dp)
                .height(40.dp),
            bitmapImage = imageBitmap,
            contentScale = ContentScale.FillHeight
        )
    }
}