package com.anbui.recipely.presentation.other_feature.camera

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
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.tensorflow.lite.support.label.Category
import org.tensorflow.lite.task.vision.classifier.Classifications

@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel(), ImageClassifierHelper.ClassifierListener {

    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var preview: Preview
    private lateinit var imageAnalyzer: ImageAnalysis
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var bitmapBuffer: Bitmap
    private var screenOrientation: Int = 0

    private val _inferenceTime = mutableStateOf(0L)
    val inferenceTime: State<Long> = _inferenceTime

    fun onChangeInferenceTime(value: Long) {
        _inferenceTime.value = value
    }

    private var _resultList = MutableStateFlow<List<Classifications>?>(null)
    val resultList = _resultList.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _results = MutableStateFlow(listOf<Category>())

    @OptIn(FlowPreview::class)
    var result = resultList
        .debounce(20)
        .onEach {
            _isSearching.update { true }
        }
        .combine(_results) { rslts, _ ->
            rslts?.let { it ->
                it[0].categories.sortedBy { it.index }
            } ?: emptyList()
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _results.value
        )

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
                // /
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

    override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
        _resultList.value = results?.toMutableList()
    }

    override fun onCleared() {
        super.onCleared()
        imageClassifierHelper.clearImageClassifier()
    }
}