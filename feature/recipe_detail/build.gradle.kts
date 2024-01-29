plugins {
    alias(libs.plugins.recipely.android.feature)
    alias(libs.plugins.recipely.android.library.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "com.anbui.recipely.feature.recipe_detail"
}

dependencies {
    api(libs.ui.util)

    implementation(libs.kotlinx.datetime)
    implementation(libs.coil.compose)

    // Media3
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
}