package com.hieppt.enrich.gnew.ui.screens.my_profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieppt.enrich.gnew.helper.getBitmapFromUri
import com.hieppt.enrich.gnew.ui.screens.common.UserAvatar
import com.hieppt.enrich.gnew.ui.theme.screenHeight

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
                    viewModel.updateInformationChangedState(true)
                }
            }

        }

    val screenState by viewModel.screenState.collectAsState()


    LaunchedEffect(key1 = null) {
        viewModel.initData()
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxHeight()
                .weight(1F, true),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserAvatar(
                modifier = Modifier
                    .height(screenHeight().times(0.4).dp),
                bitmapImage = screenState.imgBitmap,
                contentScale = ContentScale.FillHeight
            )

            Button(
                onClick = { galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp)
            ) {
                Text(text = "Select Photo From Gallery")
            }
        }

        Button(
            onClick = {
                viewModel.saveImage(
                    context = context,
                    imageBitmap = screenState.imgBitmap
                )
            },
            enabled = screenState.isInformationChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(text = "Save")
        }

    }

}