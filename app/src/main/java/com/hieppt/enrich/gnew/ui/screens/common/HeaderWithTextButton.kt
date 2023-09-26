package com.hieppt.enrich.gnew.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hieppt.enrich.gnew.ui.theme.highlightColor

@Composable
fun HeaderWithTextButton(onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Recommend Article",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 11.54.sp,
                lineHeight = 15.74.sp,
                fontWeight = FontWeight.W700
            )
        )
        Text(
            text = "Show All",
            modifier = Modifier
                .clickable(onClick = onClick),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = highlightColor,
                fontSize = 7.69.sp,
                lineHeight = 10.49.sp,
                fontWeight = FontWeight.W400
            )
        )
    }
}