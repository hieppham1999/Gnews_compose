package com.hieppt.enrich.gnew.helper

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination

val articleDetailRouteRegex = "^${ArticleDetailDestination.route}.+"

fun textWidth(text: String,fontSize : Float ,context: Context): Float {
    val bounds = Rect()
    val textPaint = Paint()
    textPaint.textSize = fontSize
    textPaint.getTextBounds(text, 0, text.length, bounds)
    return bounds.width() * (context.resources.displayMetrics.density + 0.5f) / 2
}

fun getTextHeight(text: String,fontSize : Float ,context: Context): Float {
    val bounds = Rect()
    val textPaint = Paint()
    textPaint.textSize = fontSize
    textPaint.getTextBounds(text, 0, text.length, bounds)
    return bounds.height() * (context.resources.displayMetrics.density + 0.5f) / 2
}