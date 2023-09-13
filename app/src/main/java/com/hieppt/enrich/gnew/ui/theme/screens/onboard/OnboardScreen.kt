package com.hieppt.enrich.gnew.ui.theme.screens.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.ui.theme.poppinsFontFamily

@Composable
fun OnboardScreen() {
    Box(
    ) {
        val configuration = LocalConfiguration.current

        val screenHeight = configuration.screenHeightDp.dp
        Image(
            painter = painterResource(id = R.drawable.onboard),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize(),
            contentDescription = ""
        )
        Column(modifier = Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    0F to Color.Transparent,
                    0.35F to Color.Black.copy(alpha = 0.9F),
                    1F to Color.Black.copy(alpha = 1F)
                )
            )) {
            Text(
                text = "Discover nature and explore beyond",
                fontSize = 30.sp,
                lineHeight = 46.sp,
                fontWeight = FontWeight.W600,
                fontFamily = poppinsFontFamily,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(
//                        Brush.verticalGradient(
//                            0F to Color.Transparent,
//                            0.35F to Color.Black.copy(alpha = 0.9F),
//                            1F to Color.Black.copy(alpha = 1F)
//                        )
//                    )
                    .padding(start = 20.dp, bottom = 0.22 * screenHeight),
                maxLines = 2
            )
            Row {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                    ButtonWithElevation()
                }
            }
        }



    }

}


@Composable
fun ButtonWithElevation() {
    val shape = RoundedCornerShape(10.dp)

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clip(shape = shape)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    color = Color.White
                ),
            ) {

            }
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFF2F2F2E), Color(0xFF2F2F2E).copy(alpha = 0f))
                )
            )
            .padding(10.dp)
    ) {
        Text(text = "Get Started",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600,
            fontFamily = poppinsFontFamily,
            color = Color.White)
        Image(
            painter = painterResource(id = R.drawable.icon_get_started),
            modifier = Modifier.width(0.1 * screenWidth),
            contentDescription = "",)
    }
}

@Preview
@Composable
fun OnboardScreenPreview() {
    OnboardScreen()
}