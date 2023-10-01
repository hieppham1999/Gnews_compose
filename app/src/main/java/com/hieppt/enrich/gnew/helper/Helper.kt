package com.hieppt.enrich.gnew.helper
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Paint
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

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