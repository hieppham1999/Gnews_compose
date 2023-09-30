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
import com.hieppt.enrich.gnew.ui.screens.article_detail.nav.ArticleDetailDestination
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


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

fun saveToInternalStorage(context: Context, bitmapImage: Bitmap): String? {
    val cw = ContextWrapper(context)
    // path to /data/data/yourapp/app_data/imageDir
    val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
    // Create imageDir
    val mypath = File(directory, "profile.jpg")
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(mypath)
        // Use the compress method on the BitMap object to write image to the OutputStream
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fos!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return directory.absolutePath
}

fun loadImageFromStorage(path: String): Bitmap? {
    return try {
        val f = File(path, "profile.jpg")
        BitmapFactory.decodeStream(FileInputStream(f))
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}