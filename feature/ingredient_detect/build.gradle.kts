plugins {
    alias(libs.plugins.recipely.android.feature)
    alias(libs.plugins.recipely.android.library.compose)
}

android {
    namespace = "com.anbui.recipely.feature.ingredient_detect"
}

dependencies {
    api(libs.ui.tooling.preview)
    api(libs.core.ktx)
    api(libs.material3)
    implementation(libs.activity.compose)

    // CameraX
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)
    implementation(libs.androidx.camera.video)

    // Tensorflow
    implementation(libs.tensorflow.lite.task.vision)
    implementation(libs.tensorflow.lite.gpu.delegate.plugin)
    implementation(libs.tensorflow.lite.gpu)
}