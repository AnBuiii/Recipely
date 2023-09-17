package com.anbui.recipely.presentation.camera

import androidx.camera.core.CameraSelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.tensorflow.lite.task.vision.classifier.Classifications


@Composable
fun CameraScreen(
    navController: NavController,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current

//    val permissionState = rememberMultiplePermissionsState(
//        permissions = listOf(
//            Manifest.permission.CAMERA,
//            Manifest.permission.RECORD_AUDIO
//        )
//    )

    val previewView: PreviewView = remember { PreviewView(context) }
    val videoCapture: MutableState<VideoCapture<Recorder>?> = remember { mutableStateOf(null) }

    val cameraSelector: MutableState<CameraSelector> = remember {
        mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA)
    }
    val result = remember {
        mutableStateOf(listOf<Classifications>())
    }

//    LaunchedEffect(Unit) {
//        permissionState.launchMultiplePermissionRequest()
//    }

    LaunchedEffect(Unit) {
        viewModel.setUpCamera(context, previewView, lifecycleOwner, configuration.orientation)
    }

//    LaunchedEffect(previewView) {
////        result.value = emptyList<Classifications>()
////
////        result.value[1].
//        videoCapture.value = context.createVideoCaptureUseCase(
//            lifecycleOwner = lifecycleOwner,
//            cameraSelector = cameraSelector.value,
//            previewView = previewView
//        )
//    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )


    }
}

