package com.anbui.recipely.presentation.camera

import android.Manifest
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun CameraScreen(
    navController: NavController,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

//    val permissionState = rememberMultiplePermissionsState(
//        permissions = listOf(
//            Manifest.permission.CAMERA,
//            Manifest.permission.RECORD_AUDIO
//        )
//    )

    var recording: Recording? = remember { null }
    val previewView: PreviewView = remember { PreviewView(context) }
    val videoCapture: MutableState<VideoCapture<Recorder>?> = remember { mutableStateOf(null) }
    val recordingStarted: MutableState<Boolean> = remember { mutableStateOf(false) }

    val audioEnabled: MutableState<Boolean> = remember { mutableStateOf(false) }
    val cameraSelector: MutableState<CameraSelector> = remember {
        mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA)
    }

//    LaunchedEffect(Unit) {
//        permissionState.launchMultiplePermissionRequest()
//    }

    LaunchedEffect(previewView) {
        videoCapture.value = context.createVideoCaptureUseCase(
            lifecycleOwner = lifecycleOwner,
            cameraSelector = cameraSelector.value,
            previewView = previewView
        )
    }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AndroidView(
                factory = { previewView },
                modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = {
                    if (!recordingStarted.value) {
                        videoCapture.value?.let { videoCapture ->
                            recordingStarted.value = true
                            val mediaDir = context.externalCacheDirs.firstOrNull()?.let {
                                File(it, "recipelt").apply { mkdirs() }
                            }

                            recording = startRecordingVideo(
                                context = context,
                                filenameFormat = "yyyy-MM-dd-HH-mm-ss-SSS",
                                videoCapture = videoCapture,
                                outputDirectory = if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir,
                                executor = context.mainExecutor,
                                audioEnabled = audioEnabled.value
                            ) { event ->
                                if (event is VideoRecordEvent.Finalize) {
                                    val uri = event.outputResults.outputUri
                                    if (uri != Uri.EMPTY) {
                                        val uriEncoded = URLEncoder.encode(
                                            uri.toString(),
                                            StandardCharsets.UTF_8.toString()
                                        )
//                                        navController.navigate("${Route.VIDEO_PREVIEW}/$uriEncoded")
                                    }
                                }
                            }
                        }
                    } else {
                        recordingStarted.value = false
                        recording?.stop()
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            ) {
                Icon(
                    imageVector = (if (audioEnabled.value) Icons.Default.Add else Icons.Default.Person),

                    contentDescription = "",
                    modifier = Modifier.size(64.dp)
                )
            }
            if (!recordingStarted.value) {
                TextButton(
                    onClick = {
                        audioEnabled.value = !audioEnabled.value
                    },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 32.dp)
                ) {
                    Text(
                        text = (if (audioEnabled.value) "enable" else "disable"),

                        modifier = Modifier.size(64.dp)
                    )
                }
            }
            if (!recordingStarted.value) {
                TextButton(
                    onClick = {
                        cameraSelector.value =
                            if (cameraSelector.value == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
                            else CameraSelector.DEFAULT_BACK_CAMERA
                        lifecycleOwner.lifecycleScope.launch {
                            videoCapture.value = context.createVideoCaptureUseCase(
                                lifecycleOwner = lifecycleOwner,
                                cameraSelector = cameraSelector.value,
                                previewView = previewView
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 32.dp)
                ) {
                    Text(
                        text = "Switch",
                        modifier = Modifier.size(64.dp)
                    )
                }
            }

    }
}

//@Composable
//private fun CameraContent(
//    onPhotoCaptured: (Bitmap) -> Unit,
//    lastCapturedPhoto: Bitmap? = null
//) {
//
//    val context: Context = LocalContext.current
//    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
//    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                text = { Text(text = "Take photo") },
//                onClick = { capturePhoto(context, cameraController, onPhotoCaptured) },
//                icon = { Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Camera capture icon") }
//            )
//        }
//    ) { paddingValues: PaddingValues ->
//
//        Box(modifier = Modifier.fillMaxSize()) {
//            AndroidView(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//                factory = { context ->
//                    PreviewView(context).apply {
//                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//                        setBackgroundColor(Color.BLACK)
//                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
//                        scaleType = PreviewView.ScaleType.FILL_START
//                    }.also { previewView ->
//                        previewView.controller = cameraController
//                        val cameraSelector =
//                            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
////                        cameraController.bindToLifecycle(lifecycleOwner,cameraSelector)
//
//
//                    }
//                }
//            )
//
//            if (lastCapturedPhoto != null) {
//                LastPhotoPreview(
//                    modifier = Modifier.align(alignment = BottomStart),
//                    lastCapturedPhoto = lastCapturedPhoto
//                )
//            }
//        }
//    }
//}
//
//private fun capturePhoto(
//    context: Context,
//    cameraController: LifecycleCameraController,
//    onPhotoCaptured: (Bitmap) -> Unit
//) {
//    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)
//
//    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
//        override fun onCaptureSuccess(image: ImageProxy) {
//            val correctedBitmap: Bitmap = image
//                .toBitmap()
//                .rotateBitmap(image.imageInfo.rotationDegrees)
//
//            onPhotoCaptured(correctedBitmap)
//            image.close()
//        }
//
//        override fun onError(exception: ImageCaptureException) {
//            Log.e("CameraContent", "Error capturing image", exception)
//        }
//    })
//}
//
//
//
//@Composable
//private fun LastPhotoPreview(
//    modifier: Modifier = Modifier,
//    lastCapturedPhoto: Bitmap
//) {
//
//    val capturedPhoto: ImageBitmap = remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto.asImageBitmap() }
//
//    Card(
//        modifier = modifier
//            .size(128.dp)
//            .padding(16.dp),
//        elevation = CardDefaults.cardElevation(8.dp),
//        shape = MaterialTheme.shapes.large
//    ) {
//        Image(
//            bitmap = capturedPhoto,
//            contentDescription = "Last captured photo",
//            contentScale = androidx.compose.ui.layout.ContentScale.Crop
//        )
//    }
//}
//
//fun ImageProxy.toBitmap(): Bitmap {
//    val yBuffer = planes[0].buffer // Y
//    val vuBuffer = planes[1].buffer // VU
//
//    val ySize = yBuffer.remaining()
//    val vuSize = vuBuffer.remaining()
//
//    val nv21 = ByteArray(ySize + vuSize)
//
//    yBuffer.get(nv21, 0, ySize)
//    vuBuffer.get(nv21, ySize, vuSize)
//
//    val yuvImage = YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
//    val out = ByteArrayOutputStream()
//    yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 50, out)
//    val imageBytes = out.toByteArray()
//    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//}
//
//fun Bitmap.rotateBitmap(rotationDegrees: Int): Bitmap {
//    val matrix = Matrix().apply {
//        postRotate(-rotationDegrees.toFloat())
//        postScale(-1f, -1f)
//    }
//
//    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
//}