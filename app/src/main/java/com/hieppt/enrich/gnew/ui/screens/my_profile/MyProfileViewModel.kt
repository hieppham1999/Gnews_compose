package com.hieppt.enrich.gnew.ui.screens.my_profile

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieppt.enrich.gnew.data.model.User
import com.hieppt.enrich.gnew.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(private val _userRepo : UserRepository) : ViewModel() {

    private val _screenState = MutableStateFlow(MyProfileData())

    val screenState = _screenState.asStateFlow()

    fun initData() {
        _screenState.value = MyProfileData()
        getUser()
        loadImage()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = _userRepo.getUser()
            _screenState.update { data ->
                data.copy(user = user)
            }
        }

    }

    fun updateImageBitmap(bitmap: Bitmap) {
        _screenState.update { data ->
            data.copy(imgBitmap = bitmap)
        }
    }

    fun updateInformationChangedState(isChanged: Boolean) {
        _screenState.update { data ->
            data.copy(isInformationChanged = isChanged)
        }
    }

    private fun loadImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val imgBitmap = _userRepo.getImage(path = _screenState.value.user?.avatar)
            if (imgBitmap != null) {
                updateImageBitmap(imgBitmap)
            }
        }
    }

    fun saveImage(context: Context, imageBitmap: Bitmap?) {
        viewModelScope.launch(Dispatchers.IO) {
            val path = imageBitmap?.let {
                _userRepo.saveImage(context = context, imageBitmap = imageBitmap)
                _screenState.update { data ->
                    data.copy(isInformationChanged = false)
                }
            }
        }

    }

}

data class MyProfileData constructor(
    val user: User? = null,
    val imgBitmap: Bitmap? = null,
    val isInformationChanged: Boolean = false,
)