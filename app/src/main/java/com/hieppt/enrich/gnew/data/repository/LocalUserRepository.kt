package com.hieppt.enrich.gnew.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import com.hieppt.enrich.gnew.data.User
import com.hieppt.enrich.gnew.data.api.LocalUserService
import java.io.File
import javax.inject.Inject

class LocalUserRepository @Inject constructor(private val service: LocalUserService) :UserRepository {
    @SuppressLint("SdCardPath")
    override suspend fun getUser(): User {
        return User("Hiep", avatar = "/data/user/0/com.hieppt.enrich.gnew/app_imageDir/profile.jpg")
    }

    override suspend fun saveImage(context: Context, imageBitmap: Bitmap) {
        val cw = ContextWrapper(context)
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        val file = File(directory, "profile.jpg")

        return service.saveImageToLocalStorage(file = file, imageBitmap = imageBitmap)
    }

    override suspend fun getImage(path: String?): Bitmap? {
//        val directory = context.getDir("imageDir", Context.MODE_PRIVATE)
        return path?.let { service.loadBitmapImageFromStorage(path = it) }
    }

}