package com.hieppt.enrich.gnew.ui.screens.common

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

object CustomTabShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0F, 0f)
            quadraticBezierTo((size.width / 10), 0F, x2 = (size.width / 10), y2 = (size.height / 5))
            lineTo(size.width / 10, size.height * 3 / 5)
            quadraticBezierTo(
                (size.width / 10),
                size.height * 4 / 5,
                x2 = (size.width * 2 / 10),
                y2 = (size.height * 4 / 5)
            )
            lineTo(size.width * 8 / 10, size.height * 4 / 5)
            quadraticBezierTo(
                (size.width * 9 / 10),
                size.height * 4 / 5,
                x2 = (size.width * 9 / 10),
                y2 = (size.height * 3 / 5)
            )
            lineTo(size.width * 9 / 10, size.height / 5)
            quadraticBezierTo(size.width * 9 / 10, 0F, x2 = size.width, y2 = 0F)
            close()
        }
        return Outline.Generic(path)
    }
}

class HighLightIndicatorShape(val height: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val newRect = Rect(
            offset = Offset(x = size.width * 1 / 4, y = size.height * 4 / 5 - height / 2),
            size = Size(width = size.width * 5 / 10, height = height)
        )

        val cornerRadius = CornerRadius(x = 3F, y = 3F)

        return Outline.Rounded(
            RoundRect(
                rect = newRect,
                topLeft = cornerRadius,
                topRight = cornerRadius,
                bottomRight = cornerRadius,
                bottomLeft = cornerRadius
            )
        )
    }
}