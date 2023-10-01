package com.hieppt.enrich.gnew.data.repository

import android.content.Context
import android.graphics.Bitmap
import com.hieppt.enrich.gnew.data.model.User

interface UserRepository {

    suspend fun getUser(): User
    suspend fun saveImage(context: Context, imageBitmap: Bitmap)
    suspend fun getImage(path: String?): Bitmap?
}