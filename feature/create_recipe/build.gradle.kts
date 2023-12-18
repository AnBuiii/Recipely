plugins {
    alias(libs.plugins.recipely.android.feature)
    alias(libs.plugins.recipely.android.library.compose)
//    id("kotlin-parcelize")
}

android {
    namespace = "com.anbui.recipely.feature.create_recipe"
}

dependencies {
    api(libs.ui.tooling.preview)
    api(libs.core.ktx)
    api(libs.material3)

    implementation(libs.ui.util)
    implementation(libs.activity.compose)

    implementation(libs.kotlinx.datetime)
    implementation(libs.coil.compose)

    // Media3
//    implementation(libs.androidx.media3.exoplayer)
//    implementation(libs.androidx.media3.ui)

}