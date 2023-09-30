package com.hieppt.enrich.gnew.ui.screens.my_profile

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieppt.enrich.gnew.helper.loadImageFromStorage
import com.hieppt.enrich.gnew.helper.saveToInternalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor() : ViewModel() {

    private val _screenState = MutableStateFlow(MyProfileData())

    val screenState = _screenState.asStateFlow()

    fun updateImageBitmap(bitmap: Bitmap) {
        _screenState.update { data ->
            data.copy(imgBitmap = bitmap)
        }
    }

    fun loadImage(context: Context) {
        viewModelScope.launch {
            val directory = context.getDir("imageDir", Context.MODE_PRIVATE)
            val imgBitmap = loadImageFromStorage(path = directory.absolutePath)
            if (imgBitmap != null) {
                _screenState.update {data ->
                    data.copy(imgBitmap = imgBitmap)
                }
            }
        }
    }

    fun saveImage(context: Context, imageBitmap: Bitmap?) {
        viewModelScope.launch {
            val path = imageBitmap?.let {
                saveToInternalStorage(context = context, it)


            }
            println(path)
        }

    }

}

data class MyProfileData constructor(
    val imgBitmap: Bitmap? = null
)