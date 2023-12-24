package com.example.ingredient_detect

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel(),
    ImageClassifierHelper.ClassifierListener {

    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var preview: Preview
    private lateinit var imageAnalyzer: ImageAnalysis
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var bitmapBuffer: Bitmap
    private var screenOrientation: Int = 0

    /*    private val _inferenceTime = mutableLongStateOf(0L)
        val inferenceTime: State<Long> = _inferenceTime

        fun onChangeInferenceTime(value: Long)
        }*/

    private val _uiState = MutableStateFlow(CameraScreenState())
    val uiState = _uiState.asStateFlow()

    private var _resultList = MutableStateFlow<List<Classifications>?>(null)
    val resultList = _resultList.asStateFlow()

    private val _isSearching = MutableStateFlow(false)

    @OptIn(FlowPreview::class)
    val result = resultList
        .debounce(20)
        .filterNot { _isSearching.value }
        .onEach {
            _isSearching.update { true }
        }
        .map { rslts ->
            rslts?.let { classifications ->
                classifications[0].categories.sortedBy { it.index }
            } ?: emptyList()
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun changeScan() {
        _isSearching.update {
            !it
        }
    }

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
                        .setResolutionSelector(
                            ResolutionSelector.Builder()
                                .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                                .build()
                        )
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                // ImageAnalysis. Using RGBA 8888 to match how our models work
                imageAnalyzer =
                    ImageAnalysis.Builder()
                        .setResolutionSelector(
                            ResolutionSelector.Builder()
                                .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                                .build()
                        )
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
                } catch (e: Exception) {
                    Log.e("TAG", "Use case binding failed", e)
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
        _resultList.update {
            results
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (this::imageClassifierHelper.isInitialized) {
            imageClassifierHelper.clearImageClassifier()
        }
    }
}
