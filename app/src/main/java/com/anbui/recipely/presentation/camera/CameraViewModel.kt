package com.anbui.recipely.presentation.camera

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.tensorflow.lite.support.label.Category
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
) : ViewModel(), ImageClassifierHelper.ClassifierListener {


    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var preview: Preview
    private lateinit var imageAnalyzer: ImageAnalysis
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var bitmapBuffer: Bitmap
    private var screenOrientation: Int = 0


    private var _resultList = mutableStateListOf<Category>()
    val resultList: List<Category> = _resultList

    private val _inferenceTime = mutableStateOf(0L)
    val inferenceTime: State<Long> = _inferenceTime

    fun onChangeInferenceTime(value: Long) {
        _inferenceTime.value = value
    }



//    fun onChangeResult(: ) {
//        _resultList.value =
//    }


    fun setUpCamera(
        context: Context,
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        orientation: Int
    ) {
        screenOrientation = orientation
        imageClassifierHelper =
            ImageClassifierHelper(context = context, imageClassifierListener = this)
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener(
            {
                // CameraProvider
                cameraProvider = cameraProviderFuture.get()

                // CameraExecuter
                cameraExecutor = Executors.newSingleThreadExecutor()
                ///
                // Build and bind the camera use cases

                // CameraSelector - makes assumption that we're only using the back camera
                val cameraSelector =
                    CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()

                // Preview. Only using the 4:3 ratio because this is the closest to our models
                preview =
                    Preview.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                // ImageAnalysis. Using RGBA 8888 to match how our models work
                imageAnalyzer =
                    ImageAnalysis.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                        .setTargetRotation(previewView.display.rotation)
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                        .build()
                        // The analyzer can then be assigned to the instance
                        .also {
                            it.setAnalyzer(cameraExecutor) { image ->
                                if (!::bitmapBuffer.isInitialized) {
                                    bitmapBuffer = Bitmap.createBitmap(
                                        image.width,
                                        image.height,
                                        Bitmap.Config.ARGB_8888
                                    )
                                }
                                classifyImage(image)
                            }
                        }

                cameraProvider.unbindAll()

                try {
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalyzer
                    )

                    preview.setSurfaceProvider(previewView.surfaceProvider)
                } catch (exc: Exception) {
                    Log.e("TAG", "Use case binding failed", exc)
                }
            },
            ContextCompat.getMainExecutor(context)
        )
    }

    private fun classifyImage(image: ImageProxy) {
        // Copy out RGB bits to the shared bitmap buffer
        image.use { bitmapBuffer.copyPixelsFromBuffer(image.planes[0].buffer) }

        imageClassifierHelper.classify(bitmapBuffer, screenOrientation)
    }

    override fun onError(error: String) {
        // error
    }


    override fun onResults( results: List<Classifications>?, inferenceTime: Long) {

        results?.let { it ->
            val a = it[0].categories.sortedBy { it.index }
            _resultList.clear()
            _resultList.addAll(a)
        }

    }

    override fun onCleared() {
        super.onCleared()
        imageClassifierHelper.clearImageClassifier()
    }

}