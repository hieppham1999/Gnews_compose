package com.hieppt.enrich.gnew.helper

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Paint
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination
import com.hieppt.enrich.gnew.ui.screens.discover.nav.ExploreDestination
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

fun checkRouteStartWith(text: String?, startWith: String): Boolean {
    return text?.matches(Regex("^${startWith}.*+")) ?: false
}

fun textWidth(text: String, fontSize: Float, context: Context): Float {
    val bounds = Rect()
    val textPaint = Paint()
    textPaint.textSize = fontSize
    textPaint.getTextBounds(text, 0, text.length, bounds)
    return bounds.width() * (context.resources.displayMetrics.density + 0.5f) / 2
}

fun getTextHeight(text: String, fontSize: Float, context: Context): Float {
    val bounds = Rect()
    val textPaint = Paint()
    textPaint.textSize = fontSize
    textPaint.getTextBounds(text, 0, text.length, bounds)
    return bounds.height() * (context.resources.displayMetrics.density + 0.5f) / 2
}

fun getBitmapFromUri(context: Context, selectedPhotoUri: Uri): Bitmap? {

    val bitmap = when {
        Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
            context.contentResolver,
            selectedPhotoUri
        )

        else -> {
            val source = ImageDecoder.createSource(context.contentResolver, selectedPhotoUri)
            ImageDecoder.decodeBitmap(source)
        }
    }

    return bitmap
}