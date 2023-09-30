package com.hieppt.enrich.gnew.ui.screens.my_profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hieppt.enrich.gnew.R
import com.hieppt.enrich.gnew.helper.getBitmapFromUri

@Composable
fun ProfileScreen(
    viewModel: MyProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var selectImages by remember { mutableStateOf(Uri.EMPTY) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            selectImages = it
            if (it != null) {
                getBitmapFromUri(context = context, selectedPhotoUri = it)?.let { it1 ->
                    viewModel.updateImageBitmap(
                        it1
                    )
                }
            }

        }

    val screenState by viewModel.screenState.collectAsState()


    LaunchedEffect(key1 = null ) {
        viewModel.loadImage(context = context)
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (screenState.imgBitmap != null) Image(
            bitmap = screenState.imgBitmap!!.asImageBitmap(),
            contentDescription = null,
        ) else Image(
            painter = painterResource(id = R.drawable.img_default_avatar),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Button(
            onClick = { galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
        ) {
            Text(text = "Select Photo From Gallery")
        }

        Button(
            onClick = {
                viewModel.saveImage(
                    context = context,
                    imageBitmap = screenState.imgBitmap
                )
            },
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
        ) {
            Text(text = "Save")
        }

    }

}